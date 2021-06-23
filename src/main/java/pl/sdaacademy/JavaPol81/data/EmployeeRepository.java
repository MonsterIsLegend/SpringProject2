package pl.sdaacademy.JavaPol81.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);
    List<Employee> findByNameAndLsatName(String name, String lsatName);
    List<Employee> findByLsatNameEndingWith(String ending);
}
