package pl.sdaacademy.JavaPol81.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeTransformer recipeTransformer;

    @Autowired
    public RecipeService(RecipeTransformer recipeTransformer, RecipeRepository recipeRepository) {
        this.recipeTransformer = recipeTransformer;
        this.recipeRepository = recipeRepository;
    }

    public Recipe addRecipe(Recipe recipe) {
        recipeRepository.findById(recipe.getId())
                .ifPresent(r -> {
                    throw new IllegalArgumentException("recipe already exist in db!");
                });
        recipeRepository.save(recipe);
        return recipe;
    }

    public List<RecipeDTO> getAllRecipe() {
        return recipeRepository.findAll().stream()
                .map(recipe -> recipeTransformer.toDto(recipe))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipesByName(String name) {
        return recipeRepository.findByName(name);
    }

//    public List<Recipe> getRecipesByIngredient(String ingredient) {
//        return recipeRepository.findByIngredientContains(ingredient);
//    }

    public List<Recipe> getRecipesByTimeToPrepare(int timeToPrepare) {
        return recipeRepository.findByTimeToPrepareInMin(timeToPrepare);
    }

    public Recipe removeRecipeById(int id) {
        Recipe recipeToRemove = recipeRepository.findById(id)
                .orElseThrow(()-> {
                    throw new NoSuchElementException();
                });
        recipeRepository.delete(recipeToRemove);
        return recipeToRemove;
    }

    public Recipe updateRecipeById(Recipe recipe) {
        Recipe recipeToUpdate = recipeRepository.findById(recipe.getId())
                .orElseThrow(()-> {
                    throw new NoSuchElementException();
                });

        if (recipe.getName()!= null) {
            recipeToUpdate.setName(recipe.getName());
        }
        if (recipe.getDescription() != null) {
            recipeToUpdate.setDescription(recipe.getDescription());
        }
        if (recipe.getIngredients() != null) {
            recipeToUpdate.setIngredients(recipe.getIngredients());
        }
        if (recipe.getNumberOfCalories() != 0) {
            recipeToUpdate.setNumberOfCalories(recipe.getNumberOfCalories());
        }
        if (recipe.getNumberOfPeople() != 0) {
            recipeToUpdate.setNumberOfPeople(recipe.getNumberOfPeople());
        }
        if (recipe.getTimeToPrepareInMin() != 0) {
            recipeToUpdate.setTimeToPrepareInMin(recipe.getTimeToPrepareInMin());
        }
        return recipeRepository.save(recipeToUpdate);
    }

    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id)
                .orElseThrow(()-> {
                    throw new NoSuchElementException();
                });
    }
}
