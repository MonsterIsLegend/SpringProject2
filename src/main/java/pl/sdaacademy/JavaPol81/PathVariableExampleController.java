package pl.sdaacademy.JavaPol81;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/path")
@RestController
public class PathVariableExampleController {
    private List<Movie> movies = Arrays.asList( new Movie("1", "Batman"),
            new Movie("2", "Avengers")
    );

    @GetMapping
    public List<Movie> getMovies() {
        return movies;
    }

    @GetMapping("/{title}")
    public Movie getMovieByTitle(@PathVariable String title) {
        return movies.stream().filter(movie -> title.equals(movie.title)).findFirst().get();
    }
}
