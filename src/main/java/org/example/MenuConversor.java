package org.example;

import java.util.Scanner;

public class MenuConversor {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Realizar conversión de moneda");
            System.out.println("2. Salir");

            int opcion = 0;

            try {
                opcion = Integer.parseInt(lectura.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }

            switch (opcion) {
                case 1:
                    mostrarOpcionesConversion(lectura);
                    break;
                case 2:
                    salir = true;
                    System.out.println("Finalizando la aplicación.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        lectura.close();
    }

    private static void mostrarOpcionesConversion(Scanner lectura) {
        System.out.println("Seleccione la moneda de origen:");
        System.out.println("1. USD - Dólar estadounidense");
        System.out.println("2. EUR - Euro");
        System.out.println("3. ARS - Peso argentino");
        System.out.println("4. BRL - Real brasileño");
        System.out.println("5. COP - Peso colombiano");

        String monedaOrigen = obtenerOpcionMoneda(lectura);

        System.out.println("Seleccione la moneda de destino:");
        String monedaDestino = obtenerOpcionMoneda(lectura);

        System.out.println("Ingrese el monto a convertir:");
        try {
            double monto = Double.valueOf(lectura.nextLine());
            ConsultaMoneda consultaMoneda = new ConsultaMoneda();
            Moneda resultado = consultaMoneda.consultarYGuardarMoneda(monedaOrigen, monedaDestino, monto);
            System.out.println("Conversión completada: " + resultado);
        } catch (NumberFormatException e) {
            System.out.println("Monto no válido: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String obtenerOpcionMoneda(Scanner lectura) {
        String opcion = "";
        while (true) {
            try {
                int seleccion = Integer.parseInt(lectura.nextLine());
                switch (seleccion) {
                    case 1: opcion = "USD"; break;
                    case 2: opcion = "EUR"; break;
                    case 3: opcion = "ARS"; break;
                    case 4: opcion = "BRL"; break;
                    case 5: opcion = "COP"; break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
        return opcion;
    }
}
