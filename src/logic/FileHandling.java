import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileHandling {
    public void saveFile(Currency currency, double amountToConvert, double totalConverted, double conversionRate) {
        try {
//            Gson gson = new GsonBuilder()
//                    .setPrettyPrinting()
//                    .create();

            FileWriter writer = new FileWriter("conversion_history.txt", true);
            StringBuilder record = new StringBuilder();
            record.append("[" + LocalDateTime.now() + "] ");
            record.append(amountToConvert);
            record.append(" " + currency.base_code() + "->");
            record.append(currency.target_code() + " = ");
            record.append(totalConverted);
            record.append(" Tipo de cambio: " + conversionRate + "\n");

            writer.write(String.valueOf(record));

//            JsonObject jsonObject = new JsonObject();

//            jsonObject.addProperty("base_code", currency.base_code());
//            jsonObject.addProperty("target_code", currency.target_code());
//            jsonObject.addProperty("conversion_rate", currency.conversion_rate());
//            jsonObject.addProperty("dateTime", String.valueOf(dateTime));
//            writer.write(gson.toJson(jsonObject));

            //Idea: crear un array del tipo que definas para el objeto json, y a ese array hacerle append
            //cada que agregues un nuevo registro

            //writer.write(gson.toJson(currency));
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void showHistory() {
        try {
            FileReader reader = new FileReader("conversion_history.txt");
            int value = reader.read();
            while (value != -1) {
                System.out.print((char) value);
                value = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
