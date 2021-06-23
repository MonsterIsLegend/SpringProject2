package pl.sdaacademy.JavaPol81.recipe;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class RecipeControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RecipeRepository recipeRepository;


    @Test
    void when_send_post_request_to_add_recipe_then_it_should_be_saved_in_db() throws Exception {
        //given
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"description\": \"p1\",\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"p1\",\n" +
                        "  \"numberOfCalories\": 1000,\n" +
                        "  \"numberOfPeople\": 4,\n" +
                        "  \"timeToPrepareInMin\": 60\n" +
                        "}"
        ));

        //when
        Recipe recipe = recipeRepository.findById(1).orElse(null);

        //then
        Assertions.assertNotNull(recipe);
        Assertions.assertEquals("p1",recipe.getName());
        recipeRepository.deleteAll();
    }

    @Test
    void when_send_get_request_to_get_all_recipes_then_list_should_be_returned() throws Exception {
        //given
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"description\": \"p1\",\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"p1\",\n" +
                        "  \"numberOfCalories\": 1000,\n" +
                        "  \"numberOfPeople\": 4,\n" +
                        "  \"timeToPrepareInMin\": 60\n" +
                        "}"
        ));
        //when
        //then
        mockMvc.perform(get("/recipe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo("p1")));
        recipeRepository.deleteAll();
    }
    @Test
    void when_send_get_request_to_get_recipe_by_id_recipe_should_be_returned() throws Exception {
        //given
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"description\": \"p2\",\n" +
                        "  \"id\": 2,\n" +
                        "  \"name\": \"p2\",\n" +
                        "  \"numberOfCalories\": 1200,\n" +
                        "  \"numberOfPeople\": 3,\n" +
                        "  \"timeToPrepareInMin\": 80\n" +
                        "}"
        ));
        //when

        //then
        mockMvc.perform(get("/recipe/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("p2")))
                .andExpect(jsonPath("$.id", equalTo(2)));
        recipeRepository.deleteAll();
    }
    @Test
    void when_send_update_request_to_update_recipe_by_id_recipe_should_be_updated() throws Exception {
        //given
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"description\": \"p3\",\n" +
                        "  \"id\": 3,\n" +
                        "  \"name\": \"p3\",\n" +
                        "  \"numberOfCalories\": 1300,\n" +
                        "  \"numberOfPeople\": 5,\n" +
                        "  \"timeToPrepareInMin\": 45\n" +
                        "}"
        ));
        //when
        //then
        mockMvc.perform(put("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"description\": \"p3\",\n" +
                        "  \"id\": 3,\n" +
                        "  \"name\": \"p3\",\n" +
                        "  \"numberOfCalories\": 1500,\n" +
                        "  \"numberOfPeople\": 6,\n" +
                        "  \"timeToPrepareInMin\": 55\n" +
                        "}"
        ))
                .andExpect(jsonPath("$.numberOfCalories",equalTo(1500)))
                .andExpect(jsonPath("$.name",equalTo("p3")))
                .andExpect(jsonPath("$.timeToPrepareInMin",equalTo(55)));
        recipeRepository.deleteAll();
    }
    @Test
    void when_send_delete_request_to_delete_recipe_by_id_recipe_should_be_deleted() throws Exception {
        //given
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\n" +
                        "  \"description\": \"p4\",\n" +
                        "  \"id\": 4,\n" +
                        "  \"name\": \"p4\",\n" +
                        "  \"numberOfCalories\": 1300,\n" +
                        "  \"numberOfPeople\": 5,\n" +
                        "  \"timeToPrepareInMin\": 45\n" +
                        "}"
        ));
        //when
        mockMvc.perform(delete("/recipe/4").contentType(MediaType.APPLICATION_JSON));
        //then
        mockMvc.perform(get("/recipe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(0)));
        recipeRepository.deleteAll();
    }
    /*
Napisz testy integracyjne sprawdzające poprawność działania metod RecipeControllera:
- add
- get
- get by id
- remove by id
- update
     */

}
