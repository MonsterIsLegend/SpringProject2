package pl.sdaacademy.JavaPol81.validation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RequestMapping("/book")
@RestController
public class BookController {

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book) {
        return book;
    }
}
