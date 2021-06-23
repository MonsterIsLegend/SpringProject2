package pl.sdaacademy.JavaPol81.di;

import org.springframework.stereotype.Component;

@Component
public class PolishGreetings implements Greetings {
    @Override
    public String greetings() {
        return "Dzień dobry!";
    }
}
