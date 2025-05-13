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
import java.util.*;

public class ConversorMoeda {
    Scanner sc = new Scanner(System.in);

    public void converterMoeda(String moeda1, String moeda2) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/1f290454f091ede706cc9894/latest/" + moeda1)).GET().build();
        HttpResponse<String> response = null;

        try {
            System.out.print("\nDigite o valor da moeda " + moeda1 + ": ");
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
            FormataJSONparaGSON formatado = gson.fromJson(json, FormataJSONparaGSON.class);

            Moeda moeda = new Moeda(formatado);

            double cotacao = moeda.getCotacao(moeda2);
            String codigoMoeda = moeda.getCodigo(moeda2);
            double conversao = valorMoeda * cotacao;

            SimpleDateFormat formatoBR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            formatoBR.setTimeZone(TimeZone.getTimeZone("UTC"));

            System.out.println("\nCotação do " + codigoMoeda + " com base em " + moeda.getCodigo(moeda1) + ": " + cotacao);
            System.out.printf("Conversão: %.2f %s \u001B[32m⟶\u001B[0m %.2f %s%n", valorMoeda, formatado.base_code(), conversao,codigoMoeda);
            System.out.println("\nÚltima atualização da Cotação: " + formatoBR.format(formatado.time_last_update_utc()));
            System.out.println("Próxima atualização da Cotação: " + formatoBR.format(formatado.time_next_update_utc()));

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro de conexão com a API: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Tente novamente.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}