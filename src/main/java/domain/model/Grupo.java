package domain.model;

import domain.strategy.Divisao;
import domain.value_objects.Dinheiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grupo {
    private String id;
    private String nome;
    private List<Membro> membros;

    public Grupo(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.membros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Membro> getCadaMembro() {
        return List.copyOf(membros);
    }

    public void exibirSaldos() {
        System.out.println("=================================================================");
        System.out.printf(" GRUPO: %-52s \n", this.nome.toUpperCase());
        System.out.println("=================================================================");

        System.out.printf("%-10s | %-25s | %-15s\n", "ID", "NOME", "SALDO INDIVIDUAL");
        System.out.println("-----------------------------------------------------------------");

        for (Membro u : this.membros) {
            int saldoMembro = u.getSaldoEmCentavos();

            System.out.printf("%-10s | %-25s | %-15s\n",
                    u.getId(),
                    u.getNome(),
                    Integer.toString(saldoMembro)
            );
        }
        System.out.println("-----------------------------------------------------------------");

        int saldoSoma = 0;
        for (Membro m : membros) {
            saldoSoma = saldoSoma + m.getSaldoEmCentavos();;
        }
        Dinheiro saldoTotalGrupo = new Dinheiro(saldoSoma);
        System.out.printf("%-38s | %-15s\n", "SALDO TOTAL DO GRUPO:", saldoTotalGrupo.toString());
        System.out.println("=================================================================");
    }

    public void adicionarMembro(Membro membro) {
        this.membros.add(membro);
    }
    public int getQuantidadeDeMembros(){
        return this.membros.size();
    }

    public Membro buscarMembroPorId(String id) {
        for (Membro m : membros) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public void registrarDespesa(String pagadorId, Dinheiro valorGasto, List<String> participantesIds, Divisao estrategiaDivisao) {
        Membro pagador = buscarMembroPorId(pagadorId);
        if (pagador == null) {
            throw new IllegalArgumentException("O pagador não pertence a este grupo.");
        }

        pagador.adicionarAoSaldo(valorGasto);

        Map<String, Dinheiro> divisaoAlvo = estrategiaDivisao.calcular(valorGasto, participantesIds);

        for (String idPart : participantesIds) {
            Membro participante = buscarMembroPorId(idPart);
            if (participante == null) {
                throw new IllegalArgumentException("O participante " + idPart + " não pertence a este grupo.");
            }

            Dinheiro valorDevido = divisaoAlvo.get(idPart);
            if (valorDevido != null) {
                participante.subtrairDoSaldo(valorDevido);
            }
        }
    }
}