package pl.sdaacademy.JavaPol81.recipe;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeTransformer {

    public RecipeDTO toDto(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setName(recipe.getName());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setNumberOfCalories(recipe.getNumberOfCalories());
        recipeDTO.setNumberOfPeople(recipe.getNumberOfPeople());
        recipeDTO.setTimeToPrepareInMin(recipe.getTimeToPrepareInMin());
        List<String> ingredients = recipe.getIngredients().stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
        recipeDTO.setIngredients(ingredients);
        return recipeDTO;
    }
}
