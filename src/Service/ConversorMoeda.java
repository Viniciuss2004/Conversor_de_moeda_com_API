package Service;

import Model.Moeda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

public class ConversorMoeda {
    Scanner sc = new Scanner(System.in);

    public void converterMoeda(int opcao) {
        String moeda1 = "";
        String moeda2 = "";

        switch (opcao) {
            case 1:
                moeda1 = "USD";
                moeda2 = "ARS";
                break;

            case 2:
                moeda1 = "ARS";
                moeda2 = "USD";
                break;

            case 3:
                moeda1 = "USD";
                moeda2 = "BRL";
                break;

            case 4:
                moeda1 = "BRL";
                moeda2 = "USD";
                break;

            case 5:
                moeda1 = "USD";
                moeda2 = "COP";
                break;

            case 6:
                moeda1 = "COP";
                moeda2 = "USD";
                break;

            case 7:
                System.out.println("Saindo...");
                System.exit(0);
                break;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/1f290454f091ede706cc9894/pair/" + moeda1 + "/" + moeda2)).GET().build();
        HttpResponse<String> response = null;

        try {
            System.out.println("Digite o valor da moeda " + moeda1 + ": ");
            double valorMoeda = sc.nextDouble();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro ao consultar a API. Código HTTP: " + response.statusCode());
                return;
            }

            String json = response.body();

            if (json.contains("\"result\":\"error\"")) {
                System.out.println("Erro na resposta da API: " + json);
                return;
            }

            Gson gson = new GsonBuilder().setDateFormat("E, dd MMM yyyy HH:mm:ss Z").create();
            FormataJSONparaGSON formata = gson.fromJson(json, FormataJSONparaGSON.class);

            SimpleDateFormat formatoBR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            formatoBR.setTimeZone(TimeZone.getTimeZone("UTC"));

            Moeda moeda = new Moeda(formata);
            double conversao = moeda.getCotacao() * valorMoeda;

            System.out.println("\nCotação do " + moeda2 + " com base em " + moeda1 + ": " + moeda.getCotacao());
            System.out.printf("Conversão: %.2f %s ⟶ %.2f %s%n", valorMoeda, formata.base_code(), conversao, formata.target_code());
            System.out.println("\nÚltima atualização da Cotação: " + formatoBR.format(formata.time_last_update_utc()));
            System.out.println("Próxima atualização da Cotação: " + formatoBR.format(formata.time_next_update_utc()));

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro de conexão com a API: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

    }
}
