package skx.coding;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String apiKey = System.getenv("API_KEY_PREV_TEMP");

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.print("Digite a latitude: ");
        double latitude = scanner.nextDouble();
        System.out.print("Digite a longitude: ");
        double longitude = scanner.nextDouble();

        try {
            WeatherService weatherService = new WeatherService(apiKey);
            String response = weatherService.getWeatherData(latitude, longitude);
            if (response != null) {
                WeatherData weatherData = WeatherDataParser.parseWeatherData(response);
                if (weatherData != null) {
                    weatherData.display();
                } else {
                    System.out.println("Erro ao analisar os dados do clima.");
                }
            } else {
                System.out.println("Erro ao obter dados da API.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
