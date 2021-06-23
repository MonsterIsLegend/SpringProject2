package pl.sdaacademy.JavaPol81.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/*
Należy przetestować:
- addRecipe
- getAllRecipe
- getRecipeById
- removeRecipeById
- updateRecipeById
 */
@ExtendWith(SpringExtension.class)
class RecipeServiceTest {

    @TestConfiguration
    static class TestRecipeServiceConfiguration {
        @Bean
        public RecipeService recipeService(RecipeRepository recipeRepository) {
            return new RecipeService(new RecipeTransformer(), recipeRepository);
        }
    }

    @MockBean
    RecipeRepository recipeRepository;

    @Autowired
    RecipeService recipeService;


    @Test
    void when_add_unique_recipe_to_the_db_then_it_should_be_added_to_repo() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);

        //when
        recipeService.addRecipe(recipe1);

        //then
        Mockito.verify(recipeRepository).save(recipe1);
    }

    @Test
    void when_add_recipe_that_already_exists_in_the_db_then_it_should_be_added_to_repo() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);
        Mockito.when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe1));

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()->{
            recipeService.addRecipe(recipe1);
        });
    }

    @Test
    void when_get_all_recipes_then_list_of_3_items_should_be_returned() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, Collections.emptyList(), 60, 4);
        Recipe recipe2 = new Recipe(2, "kopytka", "kopytka", 1080, Collections.emptyList(), 120, 2);
        Recipe recipe3 = new Recipe(3, "placki", "placki ziemniaczane", 500, Collections.emptyList(), 50, 3);
        Mockito.when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2, recipe3));

        //when
        List<RecipeDTO> result = recipeService.getAllRecipe();

        //then
        assertEquals(3, result.size());
    }

    @Test
    void when_get_recipe_by_id_which_is_exists_in_db_then_it_should_be_returned() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, Collections.emptyList(), 60, 4);
        Mockito.when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe1));

        //when
        Recipe recipe = recipeService.getRecipeById(1);

        //then
        assertEquals(recipe1, recipe);
    }

    @Test
    void when_remove_recipe_which_is_exists_in_db_then_it_should_be_returned_and_removed_from_storage() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, Collections.emptyList(), 60, 4);
        Mockito.when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe1));

        //when
        Recipe recipe = recipeService.removeRecipeById(1);

        //then
        Mockito.verify(recipeRepository).delete(recipe1);
        assertEquals(recipe1, recipe);
    }

    @Test
    void when_update_recipe_which_is_exists_in_db_then_it_should_be_update() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, Collections.emptyList(), 60, 4);
        Recipe recipeUpdate = new Recipe(1, "test", "test", 1000, Collections.emptyList(), 60, 4);
        Mockito.when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe1));
        Mockito.when(recipeRepository.save(recipeUpdate)).thenReturn(recipeUpdate);

        //when
        Recipe recipe = recipeService.updateRecipeById(recipeUpdate);

        //then
        Mockito.verify(recipeRepository).save(any(Recipe.class));
        assertEquals("test", recipe.getName());
    }
}