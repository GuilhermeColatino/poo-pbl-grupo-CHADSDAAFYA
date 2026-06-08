package domain.value_objects;

import java.util.Objects;

/**
 * Value Object que representa uma quantia de dinheiro em centavos.
 *
 * Usa int em centavos para evitar problemas de arredondamento com float/double.
 * Ex: R$ 10,50 = 1050 centavos
 
 */
public final class Dinheiro {

    private final int centavos;

    /**
     * Cria um Dinheiro a partir de um valor em centavos.
     *
     * @param centavos 
     * @throws IllegalArgumentException 
     */
    public Dinheiro(int centavos) {
        if (centavos < 0) {
            throw new IllegalArgumentException(
                "Valor não pode ser negativo: " + centavos
            );
        }
        this.centavos = centavos;
    }

    /**
     * Retorna o valor em centavos.
     
     *      * @return
     */
    public int getCentavos() {
        return centavos;
    }

    /**
     * Soma este Dinheiro com outro.
     *
     * @param outro 
     * @return 
     */
    public Dinheiro somar(Dinheiro outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Não é possível somar com null");
        }
        return new Dinheiro(this.centavos + outro.centavos);
    }

    /**
     * Subtrai outro Dinheiro deste.
     *
     * @param outro 
     * @return 
     * @throws IllegalArgumentException 
     */
    public Dinheiro subtrair(Dinheiro outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Não é possível subtrair null");
        }
        int resultado = this.centavos - outro.centavos;
        if (resultado < 0) {
            throw new IllegalArgumentException(
                "Resultado da subtração não pode ser negativo: " + resultado
            );
        }
        return new Dinheiro(resultado);
    }

    /**
     * Divide este Dinheiro igualmente entre N partes, sem perder centavos.
    
     
     * @param numPartes 
     * @return 
     * @throws IllegalArgumentException 
     */
    public Dinheiro[] dividir(int numPartes) {
        if (numPartes <= 0) {
            throw new IllegalArgumentException(
                "Número de partes deve ser maior que zero: " + numPartes
            );
        }

        int valorBase = this.centavos / numPartes;
        int sobra     = this.centavos % numPartes;

        Dinheiro[] partes = new Dinheiro[numPartes];

        // Primeira parte recebe a sobra para não perder centavos
        partes[0] = new Dinheiro(valorBase + sobra);

        for (int i = 1; i < numPartes; i++) {
            partes[i] = new Dinheiro(valorBase);
        }

        return partes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dinheiro dinheiro = (Dinheiro) o;
        return centavos == dinheiro.centavos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(centavos);
    }

    @Override
    public String toString() {
        return String.format("R$ %.2f", centavos / 100.0);
    }
}
