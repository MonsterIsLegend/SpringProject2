package pl.sdaacademy.JavaPol81.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RecipeService recipeService;
    @Test
    void when_send_get_request_then_list_of_recipes_should_be_returned() throws Exception {
        //given
        RecipeDTO recipeDTO1 = new RecipeDTO("p1",
                "p1",
                1000,
                60,
                4,
                Collections.emptyList());
        RecipeDTO recipeDTO2 = new RecipeDTO("p2",
                "p2",
                1200,
                80,
                3,
                Collections.emptyList());
        Mockito.when(recipeService.getAllRecipe()).thenReturn(Arrays.asList(recipeDTO1, recipeDTO2));
        //when
        //then
        mockMvc.perform(get("/recipe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo("p1")))
                .andExpect(jsonPath("$[1].name", equalTo("p2")));
    }
    @Test
    void when_send_request_to_get_recipe_by_id_then_recipe_should_be_returned() throws Exception {
        //given
        Mockito.when(recipeService.getRecipeById(1)).thenReturn(new Recipe(1, "p1", "p1", 1000, Collections.emptyList(), 60,4));
        //when
        //then
        mockMvc.perform(get("/recipe/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("p1")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }
    @Test
    void when_send_post_request_to_add_recipe_then_recipe_should_be_added() throws Exception {
        //given
        Recipe recipe = new Recipe(1, "p1", "p1", 1000, null, 60,4);
        Mockito.when(recipeService.addRecipe(recipe)).thenReturn(recipe);
        //when
        //then
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"description\": \"p1\",\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"p1\",\n" +
                "  \"numberOfCalories\": 1000,\n" +
                "  \"numberOfPeople\": 4,\n" +
                "  \"timeToPrepareInMin\": 60\n" +
                "}"))
                .andExpect(jsonPath("$.name", equalTo("p1")))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.numberOfPeople", equalTo(4)));
    }
    @Test
    void when_send_delete_by_id_request_then_recipe_should_be_deleted() throws Exception {
        //given
        Recipe recipe = new Recipe(1, "p1", "p1", 1000, null, 60,4);
        Mockito.when(recipeService.removeRecipeById(1)).thenReturn(recipe);
        //when
        //then
        mockMvc.perform(delete("/recipe/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("p1")));
    }
    @Test
    void when_send_update_request_then_recipe_should_be_updated() throws Exception {
        //given
        Recipe recipeUpdated = new Recipe(1, "p2", "p2", 1000, null, 60,4);
        Mockito.when(recipeService.updateRecipeById(recipeUpdated)).thenReturn(recipeUpdated);
        //when
        //then
        mockMvc.perform(put("/recipe").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"description\": \"p2\",\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"p2\",\n" +
                "  \"numberOfCalories\": 1000,\n" +
                "  \"numberOfPeople\": 4,\n" +
                "  \"timeToPrepareInMin\": 60\n" +
                "}"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("p2")))
                .andExpect(jsonPath("$.description", equalTo("p2")));
    }
}