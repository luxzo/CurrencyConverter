import java.util.Locale;

public class Conversion {
    private double conversion_rate;
    private double amount_toConvert;
    private double totalConverted;

    public Conversion(double conversion_rate, double amount_toConvert) {
        this.conversion_rate = conversion_rate;
        this.amount_toConvert = amount_toConvert;
    }

    public void currencyConversion() {
        totalConverted = this.conversion_rate * this.amount_toConvert;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%.4f", totalConverted);
    }
}
