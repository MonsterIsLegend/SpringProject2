package pl.sdaacademy.JavaPol81.recipe;

import java.util.List;

public class RecipeDTO {

    private String name;
    private String description;
    private int numberOfCalories;
    private int timeToPrepareInMin;
    private int numberOfPeople;
    private List<String> ingredients;

    public RecipeDTO() {
    }

    public RecipeDTO(String name, String description, int numberOfCalories, int timeToPrepareInMin, int numberOfPeople, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.numberOfCalories = numberOfCalories;
        this.timeToPrepareInMin = timeToPrepareInMin;
        this.numberOfPeople = numberOfPeople;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfCalories() {
        return numberOfCalories;
    }

    public void setNumberOfCalories(int numberOfCalories) {
        this.numberOfCalories = numberOfCalories;
    }

    public int getTimeToPrepareInMin() {
        return timeToPrepareInMin;
    }

    public void setTimeToPrepareInMin(int timeToPrepareInMin) {
        this.timeToPrepareInMin = timeToPrepareInMin;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
