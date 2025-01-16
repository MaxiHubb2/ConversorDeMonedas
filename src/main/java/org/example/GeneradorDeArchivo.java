package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class GeneradorDeArchivo {
    public void guardarJson(Moneda moneda, double monto) throws IOException {
        new GsonBuilder().setPrettyPrinting().create();

        String fileName = "conversion_" + moneda.getBase_code() + "_to_" + moneda.getTarget_code() + ".json";

        String resultado = getResultado(moneda, monto);

        // Escribir el contenido en un archivo JSON
        try (FileWriter escritura = new FileWriter(fileName)) {
            escritura.write(resultado);
            System.out.println("Archivo generado: " + fileName);
        }
    }

    private static String getResultado(Moneda moneda, double monto) {
        DecimalFormat formato = new DecimalFormat("#,###.###");
        String montoElegido = formato.format(monto);
        String resultadoConversion = formato.format(moneda.getConversion_result());

        return String.format(
                "{\n  \"Tu moneda de origen\": \"%s\",\n  \"Tu moneda destino\": \"%s\",\n  \"Monto elegido\": \"$%s %s\",\n  \"Resultado Conversion\": \"$%s %s\"\n}",
                moneda.getBase_code(),
                moneda.getTarget_code(),
                montoElegido,
                moneda.getBase_code(),
                resultadoConversion,
                moneda.getTarget_code()
        );
    }
}
