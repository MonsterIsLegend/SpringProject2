package pl.sdaacademy.JavaPol81.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/v1")
    public List<Employee> employeesByName(@RequestParam String name) {
        return employeeRepository.findByName(name);
    }

    @GetMapping("/v2")
    public List<Employee> employeesByNameAndLastName(@RequestParam String name, @RequestParam String lastName) {
        return employeeRepository.findByNameAndLsatName(name, lastName);
    }

    @GetMapping("/v3")
    public List<Employee> employeesLastNameEndingWith(@RequestParam String endingWith) {
        return employeeRepository.findByLsatNameEndingWith(endingWith);
    }

    @GetMapping
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
