package pl.sdaacademy.JavaPol81.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/weather")
@RestController
public class RestTemplateWeatherExample {

    @GetMapping("/{cityName}")
    public float getAverageTempreture(@PathVariable String cityName){
        RestTemplate restTemplate = new RestTemplate();
        String url =  String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=baa6ece140be0985d8bf8766fa381d1d&units=metric",cityName);
        Weather weather = restTemplate.getForObject(url, Weather.class);
        return (weather.getTemperature().getActualTempreture() +
                weather.getTemperature().getMinTemp() +
                weather.getTemperature().getMaxTemp() +
                weather.getTemperature().getFeelTempreture())/4;
    }






}
class Weather {
    @JsonProperty("main")
    private Temperature temperature;
    public Temperature getTemperature() {
        return temperature;
    }
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}

class Temperature {

    @JsonProperty("temp")
    private float actualTempreture;
    @JsonProperty("feels_like")
    private float feelTempreture;
    @JsonProperty("temp_min")
    private float minTemp;
    @JsonProperty("temp_max")
    private float maxTemp;

    public Temperature(float actualTempreture, float feelTempreture, float minTemp, float maxTemp) {
        this.actualTempreture = actualTempreture;
        this.feelTempreture = feelTempreture;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public Temperature() {
    }

    public float getActualTempreture() {
        return actualTempreture;
    }

    public void setActualTempreture(float actualTempreture) {
        this.actualTempreture = actualTempreture;
    }

    public float getFeelTempreture() {
        return feelTempreture;
    }

    public void setFeelTempreture(float feelTempreture) {
        this.feelTempreture = feelTempreture;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }
    /*
Napisać metodę API, która na podstawie request http://api.openweathermap.org/data/2.5/weather?q=Warszawa&appid=baa6ece140be0985d8bf8766fa381d1d
pobierze nam informacje o
 "temp": 306.09,
"feels_like": 307.74,
"temp_min": 304.8,
"temp_max": 306.99,
i obliczy nam średnią wartość z tych elementów
Api powinno umożliwiać przekazywanie dowolnej nazwy miasta
 */
}