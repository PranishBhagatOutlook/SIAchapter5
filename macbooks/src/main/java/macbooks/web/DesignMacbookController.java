package macbooks.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import macbooks.Ingredient;
import macbooks.Ingredient.Type;
import macbooks.Order;
import macbooks.Macbook;
import macbooks.User;
import macbooks.data.IngredientRepository;
import macbooks.data.MacbookRepository;
import macbooks.data.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignMacbookController {
  
  private final IngredientRepository ingredientRepo;
  
  private MacbookRepository macbookRepo;

  private UserRepository userRepo;

  @Autowired
  public DesignMacbookController(
        IngredientRepository ingredientRepo, 
        MacbookRepository macbookRepo,
        UserRepository userRepo) {
    this.ingredientRepo = ingredientRepo;
    this.macbookRepo = macbookRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public Macbook design() {
    return new Macbook();
  }
  
  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    log.info("   --- Designing macbook");
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
    
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), 
          filterByType(ingredients, type));      
    }
    
    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }

  @PostMapping
  public String processDesign(
          @Valid Macbook macbook, Errors errors,
          @ModelAttribute Order order) {

    log.info("   --- Saving macbook");

    if (errors.hasErrors()) {
      return "design";
    }

    Macbook saved = macbookRepo.save(macbook);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }
  
}
