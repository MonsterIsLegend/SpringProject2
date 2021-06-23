package pl.sdaacademy.JavaPol81.recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RequestMapping("/rest")
@RestController
public class RestTemplateTestController {
    @GetMapping
    public String testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://swapi.dev/api/people/1/?format=json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    @GetMapping("/obj")
    public StarWarsPerson testRestTemplateDeserialize() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://swapi.dev/api/people/1/?format=json";
        StarWarsPerson starWarsPerson = restTemplate.getForObject(url, StarWarsPerson.class);
        return starWarsPerson;
    }
}
class StarWarsPerson {
    private String name;
    @JsonProperty("birth_year")
    private String birthOfYear;
    public StarWarsPerson() {
    }
    public StarWarsPerson(String name, String birthOfYear) {
        this.name = name;
        this.birthOfYear = birthOfYear;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthOfYear() {
        return birthOfYear;
    }
    public void setBirthOfYear(String birthOfYear) {
        this.birthOfYear = birthOfYear;
    }
}