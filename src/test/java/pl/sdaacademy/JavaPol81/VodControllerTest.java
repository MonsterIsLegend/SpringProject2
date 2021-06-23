package pl.sdaacademy.JavaPol81;

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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VodController.class)
class VodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VodService vodService;

    @Test
    void when_request_all_vod_then_list_of_two_items_should_be_returned() throws Exception {
        //given
        Mockito.when(vodService.getVodList(null, -1, -1)).thenReturn(Arrays.asList(
                new Vod(1, "Star Wars", 1, 1),
                new Vod(2, "Batman", 1, 2)
        ));

        //when
        //then
        mockMvc.perform(get("/vod").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo("Star Wars")))
                .andExpect(jsonPath("$[1].title", equalTo("Batman")));
    }

    @Test
    void when_send_request_to_add_vod_then_added_vod_should_be_returned() throws Exception {
        //given
        Vod vod = new Vod(10, "Star Wars", 1, 1);
        Mockito.when(vodService.addVod(vod)).thenReturn(vod);

        //when
        //then
        mockMvc.perform(post("/vod").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \"id\": 10,\n" +
                "  \"numberOfEpisodes\": 1,\n" +
                "  \"seasonNumber\": 1,\n" +
                "  \"title\": \"Star Wars\"\n" +
                "}"))
                .andExpect(jsonPath("$.title", equalTo("Star Wars")))
                .andExpect(jsonPath("$.numberOfEpisodes", equalTo(1)))
                .andExpect(jsonPath("$.seasonNumber", equalTo(1)));
    }

    @Test
    void when_send__invalid_request_to_add_vod_then_error_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(post("/vod").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"numberOfEpisodes\": 1,\n" +
                        "  \"seasonNumber\": 1,\n" +
                        "  \"title\": \"Star Wars\"\n" +
                        "}"))
                .andExpect(status().is4xxClientError());
    }

}