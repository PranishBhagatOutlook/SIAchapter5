package macbooks;

import macbooks.data.IngredientRepository;
import macbooks.data.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MacbookStoreApplication {
  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo,
                                      UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.deleteAll();
        userRepo.deleteAll();

        repo.save(new Ingredient("13IN", "13.6 inch", Ingredient.Type.SCREEN));
        repo.save(new Ingredient("14IN", "14.6 inch", Ingredient.Type.SCREEN));
        repo.save(new Ingredient("BACK", "Backlit", Ingredient.Type.KEYBOARD));
        repo.save(new Ingredient("NRML", "Normal", Ingredient.Type.KEYBOARD));
        repo.save(new Ingredient("WIRD", "Wired", Ingredient.Type.MOUSE));
        repo.save(new Ingredient("WRLS", "Wireless", Ingredient.Type.MOUSE));
        repo.save(new Ingredient("35WC", "35W charger", Ingredient.Type.CHARGER));
        repo.save(new Ingredient("65WC", "65W charger", Ingredient.Type.CHARGER));
        repo.save(new Ingredient("08GR", "8 GB", Ingredient.Type.RAM));
        repo.save(new Ingredient("16GR", "16 GB", Ingredient.Type.RAM));

        userRepo.save(new User("dev", encoder.encode("dev"),
                "Pranish Bhagat", "1825 S 3rd street", "Waco", "TX",
                "76706", "650-695-2538"));
      }
    };
  }
  public static void main(String[] args) {
    SpringApplication.run(MacbookStoreApplication.class, args);
  }

}
