package pl.sdaacademy.JavaPol81.recipe;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientTransformer {

    public IngredientDTO toDto(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(ingredient.getName());
        List<String> recipes = ingredient.getRecipe().stream()
                .map(Recipe::getName)
                .collect(Collectors.toList());
        ingredientDTO.setRecipes(recipes);
        return ingredientDTO;
    }
}
