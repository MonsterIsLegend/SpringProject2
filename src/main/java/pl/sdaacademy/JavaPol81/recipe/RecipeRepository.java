package pl.sdaacademy.JavaPol81.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByName(String name);

    //List<Recipe> findByIngredientContains(String ingredient);

    List<Recipe> findByTimeToPrepareInMin(int timeToPrepareInMin);
}

/*
Napisać testy jednostkowe dla RecipeRepository uwzględniając metody:
- findByName
- findByTimeToPrepareInMin
- findById
- findAll
- save
- delete
 */