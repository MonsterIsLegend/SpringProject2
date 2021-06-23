package pl.sdaacademy.JavaPol81.di;

import org.springframework.stereotype.Component;

@Component
public class EnglishGreetings implements Greetings {

    @Override
    public String greetings() {
        return "Good morning!";
    }

}
