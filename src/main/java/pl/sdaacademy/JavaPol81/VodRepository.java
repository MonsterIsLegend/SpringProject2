package pl.sdaacademy.JavaPol81;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface VodRepository extends JpaRepository<Vod, Integer> {
    List<Vod> findByNumberOfEpisodes(int numberOfEpisodes);
    List<Vod> findBySeasonNumber(int seasonNumber);
    List<Vod> findByTitle(String title);
}

/*
1. Określić klasę Vod jako encję bazodanową
2. Stworzyć JpaRepository
3. Wykorzystali nowe repository w klasię VodController
4. Zastąpili stary VodRepository nowym
 */
