package pl.sdaacademy.JavaPol81.recipe;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {
    @Id
    private int id;
    private String name;
    private String description;
    private int numberOfCalories;
    @ManyToMany
    private List<Ingredient> ingredients;
    private int timeToPrepareInMin;
    private int numberOfPeople;

    public Recipe() {
    }

    public Recipe(int id, String name, String description, int numberOfCalories, List<Ingredient> ingredients, int timeToPrepareInMin, int numberOfPeople) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfCalories = numberOfCalories;
        this.ingredients = ingredients;
        this.timeToPrepareInMin = timeToPrepareInMin;
        this.numberOfPeople = numberOfPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && numberOfCalories == recipe.numberOfCalories && timeToPrepareInMin == recipe.timeToPrepareInMin && numberOfPeople == recipe.numberOfPeople && Objects.equals(name, recipe.name) && Objects.equals(description, recipe.description) && Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, numberOfCalories, ingredients, timeToPrepareInMin, numberOfPeople);
    }
}
