package conversiones;

import exceptions.ConversionNoSoportada;

public class ConversorAPesos {
    // Dólares estadounidenses (USD)
    private static final double USD_TO_MXN = 1 / 0.060;

    // Euros (EUR)
    private static final double EUR_TO_MXN = 1 / 0.054;

    // Dólares canadienses (CAD)
    private static final double CAD_TO_MXN = 1 / 0.079;

    // Yenes japoneses (JPY)
    private static final double JPY_TO_MXN = 1 / 8.46;

    // Yuanes chinos (CNY)
    private static final double CNY_TO_MXN = 1 / 0.43;

    // Wones surcoreanos (KRW)
    private static final double KRW_TO_MXN = 1 / 76.30;

    // Reales brasileños (BRL)
    private static final double BRL_TO_MXN = 1 / 0.28;

    // Pesos colombianos (COP)
    private static final double COP_TO_MXN = 1 / 236.11;

    // Pesos argentinos (ARS)
    private static final double ARS_TO_MXN = 1 / 16.37;

    // Libras esterlinas (GBP)
    private static final double GBP_TO_MXN = 1 / 0.047;

    public static double convertir(double cantidad, String divisa) throws ConversionNoSoportada {
        double factorConversion;
        switch (divisa) {
            case "Dólar EU (USD)":
                factorConversion = USD_TO_MXN;
                break;
            case "Euros (EUR)":
                factorConversion = EUR_TO_MXN;
                break;
            case "Dólar CA (CAD)":
                factorConversion = CAD_TO_MXN;
                break;
            case "Yen (JPY)":
                factorConversion = JPY_TO_MXN;
                break;
            case "Yuan (CNY)":
                factorConversion = CNY_TO_MXN;
                break;
            case "Won (KRW)":
                factorConversion = KRW_TO_MXN;
                break;
            case "Real (BRL)":
                factorConversion = BRL_TO_MXN;
                break;
            case "Pesos Colombianos (COP)":
                factorConversion = COP_TO_MXN;
                break;
            case "Pesos Argentinos (ARS)":
                factorConversion = ARS_TO_MXN;
                break;
            case "Libras (GBP)":
                factorConversion = GBP_TO_MXN;
                break;
            default:
                throw new ConversionNoSoportada("La conversión desde " + divisa + " no está soportada.");
        }

        double cantidadConvertida = cantidad * factorConversion;
        return cantidadConvertida;
    }
}
