package pl.sdaacademy.JavaPol81.recipe;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/*
W ramach testów controllera należy przetestować każdą metodę z wywołanego api
- add
- get
- get by id
- remove by id
- update
 */
@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipe();
    }

    @DeleteMapping("/{id}")
    public Recipe removeRecipeById(@PathVariable int id) {
        return recipeService.removeRecipeById(id);
    }

    @PutMapping
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        return recipeService.updateRecipeById(recipe);
    }

//    @GetMapping("/ingredient")
//    public List<Recipe> getAllRecipesByIngredient(@RequestParam String ingredient) {
//        return recipeService.getRecipesByIngredient(ingredient);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeController that = (RecipeController) o;
        return Objects.equals(recipeService, that.recipeService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeService);
    }
}
