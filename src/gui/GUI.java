package gui;

import conversiones.ConversorAPesos;
import conversiones.ConversorDesdePesos;
import conversiones.ConversorTemperatura;
import exceptions.ConversionNoSoportada;
import exceptions.ValorInvalido;

import javax.swing.*;

public class GUI {

    public GUI() {
        while (true) {
            mostrarMenuPrincipal();
        }
    }

    private void mostrarMenuPrincipal() {
        String[] opcionesMenuPrincipal = {"Divisas", "Temperatura", "Salir"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Seleccione una opción:",
                "Conversor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesMenuPrincipal, opcionesMenuPrincipal[0]);

        switch (opcionSeleccionada) {
            case 0:
                mostrarMenuDivisas();
                break;
            case 1:
                mostrarMenuTemperatura();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

    private void mostrarMenuDivisas() {
        String[] opcionesDivisas = {"Convetir divisas a Pesos MXN", "Convertir Pesos MXN a otra divisa"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Seleccione una opción:",
                "Conversor Divisas", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesDivisas, opcionesDivisas[0]);

        switch (opcionSeleccionada) {
            case 0:
                mostrarConversorAPesos();
                break;
            case 1:
                mostrarConversorDesdePesos();
                break;
        }
    }

    private void mostrarConversorAPesos() {
        String[] opcionesDivisas = {"Dólar EU (USD)", "Euros (EUR)", "Dólar CA (CAD)", "Yen (JPY)",
                "Yuan (CNY)", "Won (KRW)", "Real (BRL)", "Pesos Colombianos (COP)", "Pesos Argentinos (ARS)", "Libras (GBP)"};
        String divisaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione la divisa de la cual desea convertir a pesos mexicanos:",
                "Conversor A Pesos", JOptionPane.PLAIN_MESSAGE, null, opcionesDivisas, opcionesDivisas[0]);

        if (divisaSeleccionada != null) {
            String cantidadInput = JOptionPane.showInputDialog(null, "Ingrese la cantidad en " + divisaSeleccionada + ":",
                    "Conversor A Pesos", JOptionPane.PLAIN_MESSAGE);

            if (cantidadInput != null && !cantidadInput.isEmpty()) {
                try {
                    double cantidad = Double.parseDouble(cantidadInput);

                    try {
                        double cantidadConvertida = ConversorAPesos.convertir(cantidad, divisaSeleccionada);

                        JOptionPane.showMessageDialog(null,
                                String.format("%.2f %s equivalen a %.2f Pesos mexicanos", cantidad, divisaSeleccionada, cantidadConvertida),
                                "Resultado de Conversión", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ConversionNoSoportada ex) {
                        mostrarMensajeError("La conversión a pesos mexicanos desde " + divisaSeleccionada + " no está soportada.", "Error de Conversión");
                    }
                } catch (NumberFormatException ex) {
                    mostrarMensajeError("Ingrese una cantidad válida en " + divisaSeleccionada + ".", "Error de Entrada");
                }
            }
        }
    }

    private void mostrarConversorDesdePesos() {
        String[] opcionesDivisas = {"Dólar EU (USD)", "Euros (EUR)", "Dólar CA (CAD)", "Yen (JPY)",
                "Yuan (CNY)", "Won (KRW)", "Real (BRL)", "Pesos Colombianos (COP)", "Pesos Argentinos (ARS)", "Libras (GBP)"};
        String divisaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Seleccione la divisa a la cual desea convertir los pesos mexicanos:",
                "Conversor Desde Pesos", JOptionPane.PLAIN_MESSAGE, null, opcionesDivisas, opcionesDivisas[0]);

        if (divisaSeleccionada != null) {
            String cantidadInput = JOptionPane.showInputDialog(null, "Ingrese la cantidad en pesos mexicanos (MXN):",
                    "Conversor Desde Pesos", JOptionPane.PLAIN_MESSAGE);

            if (cantidadInput != null && !cantidadInput.isEmpty()) {
                try {
                    double cantidad = Double.parseDouble(cantidadInput);

                    try {
                        double cantidadConvertida = ConversorDesdePesos.convertir(cantidad, divisaSeleccionada);

                        JOptionPane.showMessageDialog(null,
                                String.format("%.2f Pesos mexicanos equivalen a %.2f %s", cantidad, cantidadConvertida, divisaSeleccionada),
                                "Resultado de Conversión", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ConversionNoSoportada ex) {
                        mostrarMensajeError("La conversión desde " + divisaSeleccionada + " no está soportada.", "Error de Conversión");
                    }
                } catch (NumberFormatException ex) {
                    mostrarMensajeError("Ingrese una cantidad válida en pesos mexicanos.", "Error de Entrada");
                }
            }
        }
    }

    private void mostrarMenuTemperatura() {
        String[] opcionesTemperatura = {"Celsius", "Fahrenheit", "Kelvin"};
        String cantidadInput = JOptionPane.showInputDialog(null, "Ingrese la Temperatura:",
                "Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE);

        if (cantidadInput != null && !cantidadInput.isEmpty()) {
            try {
                double cantidad = validarTemperatura(cantidadInput); // Llamada a validarTemperatura

                String tempPartida = (String) JOptionPane.showInputDialog(null,
                        "Seleccione la unidad de temperatura:",
                        "Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE, null, opcionesTemperatura, opcionesTemperatura[0]);

                if (tempPartida != null) {
                    String tempDestino = (String) JOptionPane.showInputDialog(null,
                            "Converti a :",
                            "Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE, null, opcionesTemperatura, opcionesTemperatura[0]);

                    if (tempDestino != null) {
                        try {
                            double temperaturaConvertida = convertirTemperatura(cantidad, tempPartida, tempDestino);

                            JOptionPane.showMessageDialog(null,
                                    String.format("%.2f° %s equivalen a %.2f° %s", cantidad, tempPartida, temperaturaConvertida, tempDestino),
                                    "Resultado de Conversión", JOptionPane.INFORMATION_MESSAGE);
                        } catch (ValorInvalido ex) {
                            mostrarMensajeError(ex.getMessage(), "Error de Conversión");
                        }
                    }
                }
            } catch (ValorInvalido ex) {
                mostrarMensajeError(ex.getMessage(), "Error de Entrada");
            }
        }
    }
    private double convertirTemperatura(double cantidad, String tempPartida, String tempDestino) throws ValorInvalido {
        double temperaturaConvertida;
        switch (tempPartida) {
            case "Celsius":
                switch (tempDestino) {
                    case "Fahrenheit":
                        temperaturaConvertida = ConversorTemperatura.celsiusToFahrenheit(cantidad);
                        break;
                    case "Kelvin":
                        temperaturaConvertida = ConversorTemperatura.celsiusToKelvin(cantidad);
                        break;
                    default:
                        throw new ValorInvalido("La conversión a " + tempDestino + " no está soportada.");
                }
                break;
            case "Fahrenheit":
                switch (tempDestino) {
                    case "Celsius":
                        temperaturaConvertida = ConversorTemperatura.fahrenheitToCelsius(cantidad);
                        break;
                    case "Kelvin":
                        temperaturaConvertida = ConversorTemperatura.fahrenheitToKelvin(cantidad);
                        break;
                    default:
                        throw new ValorInvalido("La conversión a " + tempDestino + " no está soportada.");
                }
                break;
            case "Kelvin":
                switch (tempDestino) {
                    case "Celsius":
                        temperaturaConvertida = ConversorTemperatura.kelvinToCelsius(cantidad);
                        break;
                    case "Fahrenheit":
                        temperaturaConvertida = ConversorTemperatura.kelvinToFahrenheit(cantidad);
                        break;
                    default:
                        throw new ValorInvalido("La conversión a " + tempDestino + " no está soportada.");
                }
                break;
            default:
                throw new ValorInvalido("La temperatura de partida " + tempPartida + " no está soportada.");
        }
        return temperaturaConvertida;
    }

    private double validarTemperatura(String temperaturaInput) throws ValorInvalido {
        try {
            double temperatura = Double.parseDouble(temperaturaInput);
            if (temperatura < -273.15) { // -273.15 °C es el cero absoluto
                throw new ValorInvalido("La temperatura no puede ser menor que el cero absoluto (-273.15 °C).");
            }
            return temperatura;
        } catch (NumberFormatException ex) {
            throw new ValorInvalido("Ingrese una temperatura válida.");
        }
    }

    private void mostrarMensajeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

}
