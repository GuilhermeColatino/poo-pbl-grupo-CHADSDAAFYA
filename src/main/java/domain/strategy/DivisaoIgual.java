package domain.strategy;

import domain.value_objects.Dinheiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisaoIgual implements Divisao {

    @Override
    public Map<String, Dinheiro> calcular(Dinheiro valorTotal, List<String> participantes) {

        Map<String, Dinheiro> resultado = new HashMap<>();

        int valorPorPessoa = valorTotal.getCentavos() / participantes.size();

        for (String participante : participantes) {
            resultado.put(participante, new Dinheiro(valorPorPessoa));
        }

        return resultado;
    }
}