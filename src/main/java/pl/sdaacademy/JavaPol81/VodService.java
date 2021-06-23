package pl.sdaacademy.JavaPol81;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VodService {

    private final VodRepository vodRepository;

    @Autowired
    public VodService(VodRepository vodRepository) {
        this.vodRepository = vodRepository;
    }

    public Vod addVod(Vod vod) {
        vodRepository.findById(vod.id)
                .ifPresent(v->{
                    throw new IllegalArgumentException("vod item already exists in db");
                });
        vodRepository.save(vod);
        return vod;
    }

    public List<Vod> getVodList(String title, int seasonNumber, int numberOfEpisodes) {
        if (title != null) {
            return vodRepository.findByTitle(title);
        }
        if (seasonNumber != -1) {
            return vodRepository.findBySeasonNumber(seasonNumber);
        }
        if (numberOfEpisodes != -1) {
            return vodRepository.findByNumberOfEpisodes(numberOfEpisodes);
        }
        return vodRepository.findAll();
    }

    public Vod removeVod(int id) {
        Vod item = vodRepository.findById(id)
                .orElseThrow(()->{
                    throw new NoSuchElementException("no such vod element with provided id");
                });
        vodRepository.delete(item);
        return item;
    }

    public Vod updateVod(Vod vodToUpdate) {
        Vod vod = vodRepository.findById(vodToUpdate.id)
                .orElseThrow(()->{
                    throw new NoSuchElementException("no such vod element with provided id");
                });
        if (vodToUpdate.title != null) {
            vod.title = vodToUpdate.title;
        }
        if (vodToUpdate.numberOfEpisodes != 0) {
            vod.numberOfEpisodes = vodToUpdate.numberOfEpisodes;
        }
        if (vodToUpdate.seasonNumber != 0) {
            vod.seasonNumber = vodToUpdate.seasonNumber;
        }
        vodRepository.save(vod);
        return vod;
    }

    public Vod getVodById(int id) {
        return vodRepository.findById(id)
                .orElseThrow(()->{
                    throw new NoSuchElementException("no vod item with provided id found!");
                });
    }

}
