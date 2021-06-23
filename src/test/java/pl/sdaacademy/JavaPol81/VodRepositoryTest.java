package pl.sdaacademy.JavaPol81;

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
class VodRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    VodRepository vodRepository;

    @Test
    void when_find_vod_by_titles_then_list_of_2_vod_should_be_returned() {
        //given
        String vodTitle = "Batman";
        Vod vod1 = new Vod(10, "Batman", 1, 1);
        Vod vod2 = new Vod(11, "Batman", 1, 2);
        Vod vod3 = new Vod(12, "Star Wars", 1, 1);
        testEntityManager.persist(vod1);
        testEntityManager.persist(vod2);
        testEntityManager.persist(vod3);


        //when
        List<Vod> vodList = vodRepository.findByTitle(vodTitle);

        //then
        assertEquals(2, vodList.size());
        assertEquals(vodTitle, vodList.get(0).title);
        assertEquals(vodTitle, vodList.get(1).title);
        assertEquals(1, vodList.get(0).numberOfEpisodes);
        assertEquals(2, vodList.get(1).numberOfEpisodes);
    }
}