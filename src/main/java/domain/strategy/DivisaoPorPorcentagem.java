package domain.strategy;

import domain.value_objects.Dinheiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisaoPorPorcentagem implements Divisao {

    private final Map<String, Double> porcentagens;

    public DivisaoPorPorcentagem(Map<String, Double> porcentagens) {
        this.porcentagens = porcentagens;
    }

    @Override
    public Map<String, Dinheiro> calcular(Dinheiro valorTotal, List<String> participantes) {

        Map<String, Dinheiro> resultado = new HashMap<>();

        for (String participante : participantes) {
            Double porcentagem = porcentagens.get(participante);

            if (porcentagem == null) {
                throw new IllegalArgumentException(
                        "Porcentagem não definida para " + participante
                );
            }

            int valor = (int) Math.round(
                    valorTotal.getCentavos() * (porcentagem / 100.0)
            );

            resultado.put(participante, new Dinheiro(valor));
        }

        return resultado;
    }
}