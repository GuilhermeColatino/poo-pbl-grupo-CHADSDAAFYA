package domain.model;

import domain.value_objects.Dinheiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembroTest {

    @Test
    public void criarUmMembroComSaldoInicialZero() {
        Membro membro = new Membro("membro-1", "João");

        assertEquals(0, membro.getSaldoEmCentavos());
        assertEquals("João", membro.getNome());
    }

    @Test
    public void adicionarSaldo() {
        Membro membro = new Membro("membro-1", "João");
        Dinheiro valorAReceber = new Dinheiro(5000); // R$ 50,00

        membro.adicionarAoSaldo(valorAReceber);

        assertEquals(5000, membro.getSaldoEmCentavos());
    }

    @Test
    public void subtrairSaldo() {
        Membro membro = new Membro("membro-1", "João");
        Dinheiro valorDevido = new Dinheiro(3000); // R$ 30,00

        membro.subtrairDoSaldo(valorDevido);

        // Se ele deve, o saldo fica negativo
        assertEquals(-3000, membro.getSaldoEmCentavos());
    }
}