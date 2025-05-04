package Model;

import Service.FormataJSONparaGSON;

import java.util.Date;

public class Moeda {
    private double cotacao;

    public Moeda(FormataJSONparaGSON formata) {
        this.cotacao = formata.conversion_rate();
    }

    public double getCotacao() {
        return cotacao;
    }

    @Override
    public String toString() {
        return "Cotação: " + cotacao;
    }
}
