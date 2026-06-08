package domain.model;

import domain.value_objects.Dinheiro;

public class Membro {

    private final String id;
    private final String nome;
    private int saldoEmCentavos;

    public Membro(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.saldoEmCentavos = 0;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getSaldoEmCentavos() {
        return saldoEmCentavos;
    }

    public void adicionarAoSaldo(Dinheiro valor) {
        this.saldoEmCentavos += valor.getCentavos();
    }

    public void subtrairDoSaldo(Dinheiro valor) {
        this.saldoEmCentavos -= valor.getCentavos();
    }
}