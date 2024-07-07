package skx.coding;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Locale;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    private static final String API_KEY = "861cdebea0083b4a9a824d7b94fc245d";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.print("Digite a latitude: ");
        double latitude = scanner.nextDouble();
        System.out.print("Digite a longitude: ");
        double longitude = scanner.nextDouble();

        try {
            String response = getWeatherData(latitude, longitude);
            if (response != null) {
                parseAndDisplayWeather(response);
            } else {
                System.out.println("Erro ao obter dados da API.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static String getWeatherData(double lat, double lon) throws IOException {
        String urlString = BASE_URL + "lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            System.out.println("Erro: Código de resposta HTTP " + responseCode);
            return null;
        }

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        return inline.toString();
    }

    private static void parseAndDisplayWeather(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        String cityName = jsonObject.has("name") ? jsonObject.get("name").getAsString() : "Desconhecida";
        JsonObject main = jsonObject.getAsJsonObject("main");
        double temperature = main.get("temp").getAsDouble() - 273.15;
        double feelsLike = main.get("feels_like").getAsDouble() - 273.15;
        int humidity = main.get("humidity").getAsInt();
        JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
        String description = weather.get("description").getAsString();

        System.out.println("Cidade: " + cityName);
        System.out.printf("Temperatura: %.2f°C%n", temperature);
        System.out.printf("Sensação Térmica: %.2f°C%n", feelsLike);
        System.out.println("Humidade: " + humidity + "%");
        System.out.println("Descrição: " + description);
    }
}