package pl.sdaacademy.JavaPol81.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
    @Pattern(regexp = "(\\d-?){13}", message = "must be valid ISBN number")
    private String id;
    @NotNull
    @Size(min = 4)
    private String title;
    @Size(max = 20)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}