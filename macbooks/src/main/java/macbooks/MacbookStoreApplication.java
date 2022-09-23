package macbooks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import macbooks.Ingredient.Type;
import macbooks.data.IngredientRepository;

@SpringBootApplication
public class MacbookStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(MacbookStoreApplication.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new Ingredient("13IN", "13.6 inch", Type.SCREEN));
        repo.save(new Ingredient("14IN", "14.6 inch", Type.SCREEN));
        repo.save(new Ingredient("BACK", "Backlit", Type.KEYBOARD));
        repo.save(new Ingredient("NRML", "Normal", Type.KEYBOARD));
        repo.save(new Ingredient("WIRD", "Wired", Type.MOUSE));
        repo.save(new Ingredient("WRLS", "Wireless", Type.MOUSE));
        repo.save(new Ingredient("35WC", "35W charger", Type.CHARGER));
        repo.save(new Ingredient("65WC", "65W charger", Type.CHARGER));
        repo.save(new Ingredient("08GR", "8 GB", Type.RAM));
        repo.save(new Ingredient("16GR", "16 GB", Type.RAM));
      }
    };
  }
  
}
