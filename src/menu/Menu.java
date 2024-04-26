import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private String baseCurrency;
    private String targetCurrency;
    private String amountToConvert;
    private enum currencies {USD, MXN, ARS, BRL, COP}

    public void showMainMenu() {
        showBaseCurrencyMenu();
    }

    private void showBaseCurrencyMenu() {
        String menuOption;
        System.out.println("***********************************");
        System.out.println("Convertidor de divisas\n");
        do {
            System.out.println("Divisas de origen");
            showCurrencies();
            System.out.println("0) Historial");
            System.out.println("9) Salir");
            System.out.print("Elija la opción de divisa de origen: ");
            menuOption = sc.nextLine();
            if (validateMenuOption(menuOption)) {
                setBaseCurrency(menuOption);
                System.out.println();
                showTargetCurrencyMenu();
            }
            else if (menuOption.equals("9")) {
                System.out.println("\nHasta pronto");
                sc.close();
                System.exit(0);
            } else if (menuOption.equals("0")) {
                showHistory();
            } else
                System.out.println("\nOpción no válida\n");
        } while (!menuOption.equals("9") && !validateMenuOption(menuOption));
    }

    private void showTargetCurrencyMenu() {
        String menuOption;
        do {
            System.out.println("Divisas de destino");
            showCurrencies();
            System.out.print("Elija la opción de divisa de destino: ");
            menuOption = sc.nextLine();
            if (validateMenuOption(menuOption)) {
                setTargetCurrency(menuOption);
                if (isBaseCurrencyEqualToTargetCurrency(baseCurrency, targetCurrency)) {
                    System.out.println("\n----Las divisas escogidas son iguales.---- \n----Por favor elija divisas diferentes----\n");
                    showBaseCurrencyMenu();
                }
                else {
                    checkAmountAndConvert();
                    shouldStartNewConversion();
                }
            }
            else
                System.out.println("\nOpción no válida\n");
        } while (!validateMenuOption(menuOption));
    }

    private boolean isBaseCurrencyEqualToTargetCurrency(String baseCurrency, String targetCurrency) {
        return baseCurrency.equals(targetCurrency);
    }

    private void shouldStartNewConversion() {
        String menuOption;
        do {
            System.out.print("¿Desea realizar otra conversión? 1) Sí ... 2) No: ");
            menuOption = sc.nextLine();
            if (menuOption.equals("2")) {
                System.out.println("Hasta pronto");
                sc.close();
                System.exit(0);
            }
            else if (menuOption.equals("1"))
                showBaseCurrencyMenu();
            else
                System.out.println("Intente nuevamente");
        } while(!menuOption.equals("1") || !menuOption.equals("2"));
    }

    private void checkAmountAndConvert() {
        do {
            System.out.print("Ingrese la cantidad a convertir mayor a 0 (acepta hasta 4 decimales): \n");
            amountToConvert = sc.nextLine();
            if (validateAmountToConvert(amountToConvert))
                makeConversion();
            else
                System.out.println("La cantidad ingresada no es válida");
        } while (!validateAmountToConvert(amountToConvert));
    }

    private void makeConversion() {
        CurrencyConversion currencyConversion = new CurrencyConversion(baseCurrency, targetCurrency, amountToConvert);
        currencyConversion.currencyConversionOperation();
        System.out.printf(Locale.US, "%nTasa de cambio de %s a %s = %.4f %n", baseCurrency, targetCurrency, currencyConversion.getConversion_rate());
        System.out.printf(Locale.US, "%s %s convertido a %s es: %.4f %n%n", amountToConvert, baseCurrency, targetCurrency, currencyConversion.getTotalConverted());
    }

    private boolean validateMenuOption(String menuOption) {
        int validOption = 0;
        try {
            validOption = Integer.parseInt(menuOption);
        } catch (NumberFormatException e) {
            e.getMessage();
        }
        return validOption >= 1 && validOption <= 5;
    }

    private boolean validateAmountToConvert(String amountToConvert) {
        double amount = 0.0d;
            try {
                amount = Double.parseDouble(amountToConvert);
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        return amount > 0.0;
    }

    private void showHistory() {
        FileHandling fileHandling = new FileHandling();
        System.out.println("******************************************\nHistorial de conversiones:\n");
        fileHandling.showHistory();
        System.out.println();
    }

    private void setBaseCurrency(String baseCurrencyOption) {
        switch (baseCurrencyOption) {
            case "1" -> this.baseCurrency = currencies.USD.toString();
            case "2" -> this.baseCurrency = currencies.MXN.toString();
            case "3" -> this.baseCurrency = currencies.ARS.toString();
            case "4" -> this.baseCurrency = currencies.BRL.toString();
            case "5" -> this.baseCurrency = currencies.COP.toString();
        }
    }

    private void setTargetCurrency(String targetCurrency) {
        switch (targetCurrency) {
            case "1" -> this.targetCurrency = currencies.USD.toString();
            case "2" -> this.targetCurrency = currencies.MXN.toString();
            case "3" -> this.targetCurrency = currencies.ARS.toString();
            case "4" -> this.targetCurrency = currencies.BRL.toString();
            case "5" -> this.targetCurrency = currencies.COP.toString();
        }
    }

    private void showCurrencies() {
        System.out.print("""
                1) Dólar estadounidense
                2) Peso mexicano
                3) Peso argentino
                4) Real brasileño
                5) Peso colombiano
                """);
    }
}
