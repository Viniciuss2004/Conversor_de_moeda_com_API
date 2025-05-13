package Model;

import Service.FormataJSONparaGSON;

import java.util.Map;

public class Moeda {
    private String codigo;
    private double cotacao;
    private Map<String, Double> listaDeMoedas;

    public Moeda(FormataJSONparaGSON formata) {
        this.cotacao = formata.conversion_rate();
        this.listaDeMoedas = formata.conversion_rates();
    }

    public String getCodigo(String moeda2) {
        return listaDeMoedas.containsKey(moeda2.toUpperCase()) ? moeda2.toUpperCase() : "Código não encontrado";
    }

    public double getCotacao(String moeda2) {
        for (Map.Entry<String, Double> entry : listaDeMoedas.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(moeda2)) {
                return entry.getValue();
            }
        }
        return 0.0;
    }

    public Map<String, Double> getListaDeMoedas() {
        return listaDeMoedas;
    }
}