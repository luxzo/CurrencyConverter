public class Main {
    public static void main(String[] args) {
        Currency currency = new HttpConnection("CAD", "MXN").pairCurrency();
        System.out.println(currency.conversion_rate());
        Conversion conversion = new Conversion(currency.conversion_rate(), 5000);
        conversion.currencyConversion();
        System.out.println(conversion);
    }
}
