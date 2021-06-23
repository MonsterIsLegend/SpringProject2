package pl.sdaacademy.JavaPol81.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    private int id;
    private String name;
    private String lsatName;

    public Employee() {

    }

    public Employee(int id, String name, String lsatName) {
        this.id = id;
        this.name = name;
        this.lsatName = lsatName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLsatName() {
        return lsatName;
    }

    public void setLsatName(String lsatName) {
        this.lsatName = lsatName;
    }
}
