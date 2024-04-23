import java.util.Locale;

public class ConversionOfCurrency {
    private double conversion_rate;
    private double amount_toConvert;
    private double totalConverted;
    private String baseCurrency;
    private String targetCurrency;

    public ConversionOfCurrency(String baseCurrency, String targetCurrency, String amount_toConvert) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.amount_toConvert = Double.parseDouble(amount_toConvert);
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

    public double getTotalConverted() {
        return totalConverted;
    }

    public void currencyConversionOperation() {
        Currency currency = new HttpConnection(baseCurrency, targetCurrency).pairCurrency();
        conversion_rate = currency.conversion_rate();
        totalConverted = conversion_rate * amount_toConvert;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%.4f", totalConverted);
    }
}
