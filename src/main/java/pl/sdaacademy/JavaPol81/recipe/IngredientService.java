package pl.sdaacademy.JavaPol81.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
Swtórz klasę IngredientDTO, która będzie rozwiązywać problem cyklicznej zależności
Stwórz klasę IngredientTransformer która przemapuje obiekty ingredient na obiekty dto
Transformer powinien zostać użyty w serwisie w metodzie getAllIngredients
 */
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientTransformer ingredientTransformer;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository,
                             IngredientTransformer ingredientTransformer) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientTransformer = ingredientTransformer;
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(ingredient -> ingredientTransformer.toDto(ingredient))
                .collect(Collectors.toList());
    }
}
