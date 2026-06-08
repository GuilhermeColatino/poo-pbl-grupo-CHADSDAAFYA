package domain.strategy;

import domain.value_objects.Dinheiro;
import java.util.List;
import java.util.Map;

public interface Divisao {
    Map<String, Dinheiro> calcular(Dinheiro valorTotal, List<String> participantes);
}