package conversiones;

import exceptions.ConversionNoSoportada;
//El valor de las divisas fue obtenido el dia 29/07/2023
public class ConversorDesdePesos {
    // Dólares estadounidenses (USD)
    private static final double MXN_TO_USD = 0.060;

    // Euros (EUR)
    private static final double MXN_TO_EUR = 0.054;

    // Dólares canadienses (CAD)
    private static final double MXN_TO_CAD = 0.079;

    // Yenes japoneses (JPY)
    private static final double MXN_TO_JPY = 8.46;

    // Yuanes chinos (CNY)
    private static final double MXN_TO_CNY = 0.43;

    // Wones surcoreanos (KRW)
    private static final double MXN_TO_KRW = 76.30;

    // Reales brasileños (BRL)
    private static final double MXN_TO_BRL = 0.28;

    // Pesos colombianos (COP)
    private static final double MXN_TO_COP = 236.11;

    // Pesos argentinos (ARS)
    private static final double MXN_TO_ARS = 16.37;

    // Libras esterlinas (GBP)
    private static final double MXN_TO_GBP = 0.047;

    public static double convertir(double cantidad, String divisa) throws ConversionNoSoportada {
        double factorConversion;

        switch (divisa) {
            case "Dólar EU (USD)":
                factorConversion = MXN_TO_USD;
                break;
            case "Euros (EUR)":
                factorConversion = MXN_TO_EUR;
                break;
            case "Dólar CA (CAD)":
                factorConversion = MXN_TO_CAD;
                break;
            case "Yen (JPY)":
                factorConversion = MXN_TO_JPY;
                break;
            case "Yuan (CNY)":
                factorConversion = MXN_TO_CNY;
                break;
            case "Won (KRW)":
                factorConversion = MXN_TO_KRW;
                break;
            case "Real (BRL)":
                factorConversion = MXN_TO_BRL;
                break;
            case "Pesos Colombianos (COP)":
                factorConversion = MXN_TO_COP;
                break;
            case "Pesos Argentinos (ARS)":
                factorConversion = MXN_TO_ARS;
                break;
            case "Libras (GBP)":
                factorConversion = MXN_TO_GBP;
                break;
            default:
                throw new ConversionNoSoportada("La conversión a " + divisa + " no está soportada.");
        }

        double cantidadConvertida = cantidad * factorConversion;
        return cantidadConvertida;
    }
}
