package pl.sdaacademy.JavaPol81;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/vod")
@RestController
public class VodController {

    private final VodService vodService;

    @Autowired
    public VodController(VodService vodService) {
        this.vodService = vodService;
    }

    @PostMapping
    public Vod addVod(@Valid @RequestBody Vod vod) {
        return vodService.addVod(vod);
    }

    @GetMapping
    public List<Vod> getVodList(@RequestParam(required = false) String title,
                                @RequestParam(required = false, defaultValue = "-1") int seasonNumber,
                                @RequestParam(required = false, defaultValue = "-1") int numberOfEpisodes) {
        return vodService.getVodList(title, seasonNumber, numberOfEpisodes);
    }

    @DeleteMapping("/{id}")
    public Vod removeVod(@PathVariable int id) {
        return vodService.removeVod(id);
    }

    @PutMapping
    public Vod updateVod(@RequestBody Vod vodToUpdate) {
        return vodService.updateVod(vodToUpdate);
    }

    @GetMapping("/{id}")
    public Vod getVodById(@PathVariable int id) {
        return vodService.getVodById(id);
    }
}