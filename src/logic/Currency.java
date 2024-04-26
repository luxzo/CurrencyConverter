import java.time.LocalDateTime;

public record Currency(String base_code, String target_code, double conversion_rate) {
    static String dateTime = String.valueOf(LocalDateTime.now());

    public static String getDateTime() {
        return dateTime;
    }
}
