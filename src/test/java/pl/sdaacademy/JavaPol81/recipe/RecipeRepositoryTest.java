package pl.sdaacademy.JavaPol81.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void when_find_recipes_by_name_then_list_of_2_recipes_should_be_returned() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);
        Recipe recipe2 = new Recipe(2, "kopytka", "kopytka", 1080, null, 120, 2);
        Recipe recipe3 = new Recipe(3, "placki", "placki ziemniaczane", 500, null, 50, 3);
        testEntityManager.persist(recipe1);
        testEntityManager.persist(recipe2);
        testEntityManager.persist(recipe3);

        //when
        List<Recipe> recipes = recipeRepository.findByName("kopytka");

        //then
        assertEquals(2, recipes.size());
        assertEquals(1000, recipes.get(0).getNumberOfCalories());
        assertEquals(1080, recipes.get(1).getNumberOfCalories());
    }

    @Test
    void when_find_recipes_by_time_to_prepare_then_list_of_2_recipes_should_be_returned() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);
        Recipe recipe2 = new Recipe(2, "kopytka", "kopytka", 1080, null, 120, 2);
        Recipe recipe3 = new Recipe(3, "placki", "placki ziemniaczane", 500, null, 60, 3);
        testEntityManager.persist(recipe1);
        testEntityManager.persist(recipe2);
        testEntityManager.persist(recipe3);

        //when
        List<Recipe> recipes = recipeRepository.findByTimeToPrepareInMin(60);

        //then
        assertEquals(2, recipes.size());
        assertEquals("kopytka", recipes.get(0).getName());
        assertEquals("placki", recipes.get(1).getName());
    }

    @Test
    void when_find_recipe_by_id_then_recipe_should_be_returned (){
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);
        testEntityManager.persist(recipe1);

        //when
        Recipe recipe = recipeRepository.findById(1).get();

        //then
        assertNotNull(recipe);
        assertEquals("kopytka", recipe.getName());
        assertEquals("kopytka", recipe.getDescription());
    }

    @Test
    void when_find_recipe_by_id_that_not_exist_in_db_then_recipe_should_not_be_returned (){
        //given

        //when
        Recipe recipe = recipeRepository.findById(1).orElse(null);

        //then
        assertNull(recipe);
    }

    @Test
    void when_save_recipe_then_it_should_be_added_to_db() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);

        //when
        Recipe addedRecipe = recipeRepository.save(recipe1);

        //then
        long count = (long) testEntityManager.getEntityManager().createQuery("select count(*) from Recipe").getSingleResult();
        assertNotNull(addedRecipe);
        assertEquals(1, count);
    }

    @Test
    void when_delete_recipe_then_it_should_be_removed_from_db() {
        //given
        Recipe recipe1 = new Recipe(1, "kopytka", "kopytka", 1000, null, 60, 4);
        Recipe recipe2 = new Recipe(2, "kopytka", "kopytka", 1080, null, 120, 2);
        testEntityManager.persist(recipe1);
        testEntityManager.persist(recipe2);

        //when
        recipeRepository.delete(recipe2);

        //then
        long count = (long) testEntityManager.getEntityManager().createQuery("select count(*) from Recipe").getSingleResult();
        assertEquals(1, count);
    }
}

/*
Napisać testy jednostkowe dla RecipeRepository uwzględniając metody:
- findByName
- findByTimeToPrepareInMin
- findById
- findAll
- save
- delete
 */