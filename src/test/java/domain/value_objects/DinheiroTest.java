package domain.value_objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DinheiroTest {

    @Test
    public void somarValoresEmCentavos() {
        //Dinheiro é colocado em centavos para evitar o uso de float
         Dinheiro dinheiro1 = new Dinheiro(1050);
         Dinheiro dinheiro2 = new Dinheiro(220);

         Dinheiro resultado = dinheiro1.somar(dinheiro2);

         assertEquals(1270, resultado.getCentavos());
}

    @Test
    public void subtrairValoresEmCentavos() {
        Dinheiro dinheiro1 = new Dinheiro(1000);
        Dinheiro dinheiro2 = new Dinheiro(300);

        Dinheiro resultado = dinheiro1.subtrair(dinheiro2);

        assertEquals(700, resultado.getCentavos());
    }

    @Test
    public void dividirValorIgualmenteEvitandoPerdaDeCentavos() {
        Dinheiro dinheiro1 = new Dinheiro(1000);

        // No nosso sistema, a divisão deve retornar um array para tratar sobras
        Dinheiro[] partes = dinheiro1.dividir(3);

        // R$ 10,00 / 3 não dá exato (3.3333...).
        // O sistema deve dar 3.34 para o primeiro e 3.33 para os outros dois para fechar os 10 reais!
        assertEquals(334, partes[0].getCentavos());
        assertEquals(333, partes[1].getCentavos());
        assertEquals(333, partes[2].getCentavos());
    }

    @Test
    public void naoDevePermitirAdicionarDinheiroNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Dinheiro(-100);
        });
    }
}