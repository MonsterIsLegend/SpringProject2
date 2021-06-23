package pl.sdaacademy.JavaPol81;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VodServiceTest {

    @TestConfiguration
    static class TestVodService {
        @Bean
        public VodService vodService(VodRepository vodRepository) {
            return new VodService(vodRepository);
        }
    }

    @Autowired
    VodService vodService;

    @MockBean
    VodRepository vodRepository;

    @Test
    void when_save_vod_not_existing_in_db_then_vod_should_be_saved_to_repo() {
        //given
        Vod vod = new Vod(1, "Star Wars", 1,1);

        //when
        vodService.addVod(vod);

        //then
        Mockito.verify(vodRepository).save(vod); //sprawdzamy, że na mocku została wywołana metoda save z przekazanym obiektem vod
    }

    @Test
    void when_save_vod_existing_in_db_then_vod_exception_should_be_thrown() {
        //given
        Vod vod = new Vod(1, "Star Wars", 1,1);
        Mockito.when(vodRepository.findById(1)).thenReturn(Optional.of(vod));

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()->{
            vodService.addVod(vod);
        }, "vod item already exists in db");
    }

    @Test
    void when_get_all_vod_from_db_then_list_of_3_vod_should_be_returned() {
        //given
        List<Vod> vodList = Arrays.asList(new Vod(1, "Star Wars", 1,1),
                new Vod(1, "Star Wars", 1,2),
                new Vod(1, "Star Wars", 1,3));
        Mockito.when(vodRepository.findAll()).thenReturn(vodList);
        //when
        List<Vod> result = vodService.getVodList(null, -1, -1);
        //then
        assertEquals(3, result.size());
        assertEquals(2, result.get(1).getNumberOfEpisodes());
    }

}