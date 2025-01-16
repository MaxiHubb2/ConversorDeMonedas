package org.example;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = "fd89bd34ce55abf915ba5499";


    public Moneda consultarYGuardarMoneda(String monedaOrigen, String monedaDestino, double monto) {
        String url = BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + monto;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error al consultar la API: " + response.body());
            }

            // Convertir la respuesta JSON a objeto Moneda
            Moneda moneda = new Gson().fromJson(response.body(), Moneda.class);

            // Redondear el resultado de la conversión a 2 decimales
            BigDecimal conversionResult = BigDecimal.valueOf(moneda.getConversion_result()).setScale(2, RoundingMode.HALF_UP);
            moneda.setConversion_result(conversionResult.doubleValue());

            // Mostrar el resultado de la conversión en consola
            System.out.println(monto + " " + monedaOrigen + " son " + moneda.getConversion_result() + " " + monedaDestino);

            // Guardar el objeto en un archivo JSON con el nuevo formato
            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(moneda, monto);

            return moneda;
        } catch (Exception e) {
            throw new RuntimeException("Error al realizar la consulta de moneda: " + e.getMessage(), e);
        }
    }
}
