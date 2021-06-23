package pl.sdaacademy.JavaPol81.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/greetings")
@RestController
public class GreetingsController {

//    @Qualifier("polishGreetings")
//    @Autowired
    public final Greetings greetings;

    @Autowired
    public GreetingsController(@Qualifier("polishGreetings")Greetings greetings) {
        this.greetings = greetings;
    }

    @GetMapping
    public String greetings() {
        return greetings.greetings();
    }
}
