package macbooks.data;

import org.springframework.data.repository.CrudRepository;

import macbooks.Ingredient;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
