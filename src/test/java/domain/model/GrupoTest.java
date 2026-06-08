package domain.model;

import domain.strategy.Divisao;
import domain.strategy.DivisaoIgual;
import domain.value_objects.Dinheiro;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrupoTest {

    @Test
    public void adicionarMembrosAoGrupo() {
        Grupo grupo = new Grupo("grupo-1", "República Central");
        Membro joao = new Membro("joao-id", "João");

        grupo.adicionarMembro(joao);

        // Garante que o grupo agora tem 1 membro cadastrado
        assertEquals(1, grupo.getQuantidadeDeMembros());
    }

    @Test
    public void calcularSaldosDosMembrosAposUmaDespesaDivididaIgualmente() {
        //Criar o grupo e colocar 3 amigos nele
        Grupo grupo = new Grupo("grupo-1", "Viagem Fim de Ano");
        Divisao divisao = new DivisaoIgual();
        Membro joao = new Membro("joao-id", "João");
        Membro maria = new Membro("maria-id", "Maria");
        Membro jose = new Membro("jose-id", "José");

        grupo.adicionarMembro(joao);
        grupo.adicionarMembro(maria);
        grupo.adicionarMembro(jose);

        //João pagou uma conta de R$ 90,00 (9000 centavos) dividida igualmente para os 3
        Dinheiro valorGasto = new Dinheiro(9000);
        List<String> participantesIds = List.of("joao-id", "maria-id", "jose-id");

        // Aqui simulamos o metodo de negócio do grupo recebendo a conta
        grupo.registrarDespesa("joao-id", valorGasto, participantesIds, divisao);

        // João colocou 90 e gastou 30 -> Fica com saldo positivo de R$ 60,00 (+6000)
        // Maria não colocou nada e gastou 30 -> Fica com saldo negativo de R$ 30,00 (-3000)
        // José não colocou nada e gastou 30 -> Fica com saldo negativo de R$ 30,00 (-3000)
        assertEquals(6000, grupo.buscarMembroPorId("joao-id").getSaldoEmCentavos());
        assertEquals(-3000, grupo.buscarMembroPorId("maria-id").getSaldoEmCentavos());
        assertEquals(-3000, grupo.buscarMembroPorId("jose-id").getSaldoEmCentavos());
    }
}