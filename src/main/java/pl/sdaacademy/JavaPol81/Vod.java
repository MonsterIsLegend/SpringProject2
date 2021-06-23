package pl.sdaacademy.JavaPol81;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Vod {
    
    @Id
    @Min(10)
    int id;

    @Size(min = 4, max = 30)
    @NotNull
    String title;

    @Min(0)
    int seasonNumber;
    @Min(0)
    int numberOfEpisodes;

    public Vod() {
    }

    public Vod(int id, String title, int seasonNumber, int numberOfEpisodes) {
        this.id = id;
        this.title = title;
        this.seasonNumber = seasonNumber;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vod vod = (Vod) o;
        return id == vod.id && seasonNumber == vod.seasonNumber && numberOfEpisodes == vod.numberOfEpisodes && Objects.equals(title, vod.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, seasonNumber, numberOfEpisodes);
    }
}