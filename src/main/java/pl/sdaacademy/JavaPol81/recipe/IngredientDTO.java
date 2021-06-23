package pl.sdaacademy.JavaPol81.recipe;

import java.util.List;

public class IngredientDTO {

    private String name;
    private List<String> recipes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }
}
