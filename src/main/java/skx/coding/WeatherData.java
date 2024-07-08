package skx.coding;

public class WeatherData {

    private String cityName;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private String description;

    public WeatherData(String cityName, double temperature, double feelsLike, int humidity, String description) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.description = description;
    }

    public void display() {
        System.out.println("Cidade: " + cityName);
        System.out.printf("Temperatura: %.2f°C%n", temperature);
        System.out.printf("Sensação Térmica: %.2f°C%n", feelsLike);
        System.out.println("Humidade: " + humidity + "%");
        System.out.println("Descrição: " + description);
    }
}
