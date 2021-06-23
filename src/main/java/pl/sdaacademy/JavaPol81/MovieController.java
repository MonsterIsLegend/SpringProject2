package pl.sdaacademy.JavaPol81;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/movie")
@RestController
public class MovieController {

    @GetMapping
    public Movie getMovie() {
        return new Movie("Start Wars", "Force Awaken");
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movie;
    }

    @GetMapping("/v2")
    public Movie addMovie(@RequestParam(required = false, defaultValue = "") String title,
                          @RequestParam(required = false, defaultValue = "unknown") String description) {
        return new Movie(title, description);
    }
}

class Movie {
    String title;
    String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
