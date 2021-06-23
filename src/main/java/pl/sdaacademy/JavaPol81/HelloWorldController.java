package pl.sdaacademy.JavaPol81;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/hello")
@RestController
public class HelloWorldController {

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/v1")
    public String helloWorld1() {
        return "Hello World1";
    }

    @GetMapping("/fullName")
    public String showFullName() {
        return "Piotr Brzozowksi";
    }

    @PostMapping("/post")
    public String postExample() {
        return "Post method invocation";
    }
}
