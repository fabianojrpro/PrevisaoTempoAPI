package skx.coding;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class WeatherDataParser {

    public static WeatherData parseWeatherData(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        String cityName = jsonObject.has("name") ? jsonObject.get("name").getAsString() : "Desconhecida";
        JsonObject main = jsonObject.getAsJsonObject("main");
        double temperature = main.get("temp").getAsDouble() - 273.15;
        double feelsLike = main.get("feels_like").getAsDouble() - 273.15;
        int humidity = main.get("humidity").getAsInt();
        JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
        String description = weather.get("description").getAsString();

        return new WeatherData(cityName, temperature, feelsLike, humidity, description);
    }
}
