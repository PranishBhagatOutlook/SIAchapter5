package macbooks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import macbooks.Ingredient.Type;
import macbooks.data.IngredientRepository;
import macbooks.data.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.deleteAll();
                userRepo.deleteAll();

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

                userRepo.save(new User("activenotprod", encoder.encode("activenotprod"),
                        "Pranish Bhagat", "1825 S 3rd street", "Waco", "TX",
                        "76706", "650-695-2538"));
            }
        };
    }

}