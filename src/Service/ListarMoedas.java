package Service;

import Model.Moeda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class ListarMoedas {
    Scanner sc = new Scanner(System.in);

    public void listarMoedas() {
        System.out.println("\u001B[32m\nOBS: Digite apenas o código da moeda, por exemplo (BRL, USD, EUR...)\u001B[0m");
        System.out.print("\nDigite a moeda base para listar as moedas e cotações: ");
        String codigoMoeda = sc.nextLine();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/1f290454f091ede706cc9894/latest/" + codigoMoeda)).GET().build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("E, dd MMM yyyy HH:mm:ss Z").create();
            FormataJSONparaGSON formatado = gson.fromJson(json, FormataJSONparaGSON.class);
            Moeda moeda = new Moeda(formatado);

            System.out.println("\n\u001B[32m==== COTAÇÕES ATUAIS EM RELAÇÃO AO " + moeda.getCodigo(codigoMoeda) + " ====\u001B[0m");

            for (Map.Entry<String, Double> entry : moeda.getListaDeMoedas().entrySet()) {
                System.out.printf("%s : %.2f\n", entry.getKey(), entry.getValue());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
