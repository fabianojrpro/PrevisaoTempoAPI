package skx.coding;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {

    private static final String API_KEY = "861cdebea0083b4a9a824d7b94fc245d";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    public String getWeatherData(double lat, double lon) throws IOException {
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
}
