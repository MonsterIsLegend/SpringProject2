package pl.sdaacademy.JavaPol81;

import org.junit.jupiter.api.Assertions;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class VodControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    VodRepository vodRepository;
    @Test
    void when_send_post_request_to_add_vod_then_it_should_be_saved_in_db() throws Exception {
        //given
        mockMvc.perform(post("/vod").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"id\": 10,\n" +
                "  \"numberOfEpisodes\": 1,\n" +
                "  \"seasonNumber\": 1,\n" +
                "  \"title\": \"Star Wars\"\n" +
                "}"));
        //when
        Vod vod = vodRepository.findById(10).orElse(null);
        //then
        Assertions.assertNotNull(vod);
        Assertions.assertEquals("Star Wars", vod.title);
    }
    @Test
    void when_send_get_request_to_get_all_vod_then_list_should_be_returned() throws Exception {
        //given
        mockMvc.perform(post("/vod").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"id\": 10,\n" +
                "  \"numberOfEpisodes\": 1,\n" +
                "  \"seasonNumber\": 1,\n" +
                "  \"title\": \"Star Wars\"\n" +
                "}"));
        //when
        //then
        mockMvc.perform(get("/vod").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", equalTo("Star Wars")));
    }
}