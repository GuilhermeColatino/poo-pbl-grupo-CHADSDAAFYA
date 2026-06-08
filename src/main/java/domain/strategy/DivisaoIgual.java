package domain.strategy;

import domain.value_objects.Dinheiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisaoIgual implements Divisao {

    @Override
    public Map<String, Dinheiro> calcular(Dinheiro valorTotal, List<String> participantes) {
        Map<String, Dinheiro> resultado = new HashMap<>();

        if (participantes == null || participantes.isEmpty()) {
            return resultado;
        }

        Dinheiro[] partes = valorTotal.dividir(participantes.size());

        for (int i = 0; i < participantes.size(); i++) {
            resultado.put(participantes.get(i), partes[i]);
        }

        return resultado;
    }
}