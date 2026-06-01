package domain.strategy;

import domain.value_objects.Dinheiro;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DivisaoTest {

    @Test
    public void dividirContaIgualmenteEntreOsParticipantes() {
        Divisao estrategia = new DivisaoIgual();
        Dinheiro valorTotal = new Dinheiro(3000); // R$ 30,00
        List<String> participantes = List.of("Joao", "Maria", "Jose");

        // Retorna um dicionario dizendo quanto cada ID deve pagar
        Map<String, Dinheiro> divisao = estrategia.calcular(valorTotal, participantes);

        assertEquals(1000, divisao.get("Joao").getCentavos());
        assertEquals(1000, divisao.get("Maria").getCentavos());
        assertEquals(1000, divisao.get("Jose").getCentavos());
    }

    @Test
    public void dividirContaPorPorcentagem() {
        // Joao vai pagar 60% e Maria 40%
        Map<String, Double> porcentagens = Map.of("Joao", 60.0, "Maria", 40.0);
        Divisao estrategia = new DivisaoPorPorcentagem(porcentagens);

        Dinheiro valorTotal = new Dinheiro(1000); // R$ 10,00
        List<String> participantes = List.of("Joao", "Maria");

        Map<String, Dinheiro> divisao = estrategia.calcular(valorTotal, participantes);

        assertEquals(600, divisao.get("Joao").getCentavos()); // R$ 6,00
        assertEquals(400, divisao.get("Maria").getCentavos()); // R$ 4,00
    }
}
