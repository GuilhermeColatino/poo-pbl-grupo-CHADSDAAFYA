package domain.model;

import domain.value_objects.Dinheiro;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String id;
    private String nome;
    private List<Membro> membros;

    public Grupo(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.membros = new ArrayList<>();
    }

    public void adicionarMembro(Membro membro) {
        this.membros.add(membro);
    }
    public int getQuantidadeDeMembros(){
        return this.membros.size();
    }
    // 1º Método Novo: Busca um membro na lista pelo ID informado
    public Membro buscarMembroPorId(String id) {
        for (Membro m : membros) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null; // Caso não encontre (o teste assume que vai encontrar)
    }

    // 2º Método Novo: Registra a despesa e faz a divisão dos saldos
    public void registrarDespesa(String pagadorId, Dinheiro valorGasto, List<String> participantesIds) {
        int totalCentavos = valorGasto.getCentavos();
        int quantidadeParticipantes = participantesIds.size();

        // Calcula quanto cada um deve pagar (no exemplo: 9000 / 3 = 3000)
        Dinheiro valorPorParticipante = new Dinheiro(totalCentavos / quantidadeParticipantes);

        // 1. Quem pagou recebe o crédito do valor TOTAL que saiu do bolso dele
        Membro pagador = buscarMembroPorId(pagadorId);
        if (pagador != null) {
            pagador.adicionarAoSaldo(valorGasto);
        }

        // 2. Desconta a parte correspondente de cada participante da lista
        for (String idPart : participantesIds) {
            Membro participante = buscarMembroPorId(idPart);
            if (participante != null) {
                // Como eles estão gastando/devendo, passamos o valor negativo
                participante.subtrairDoSaldo(valorPorParticipante);
            }
        }
    }
}