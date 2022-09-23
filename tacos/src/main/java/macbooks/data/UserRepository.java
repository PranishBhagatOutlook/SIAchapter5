package macbooks.data;
import org.springframework.data.repository.CrudRepository;
import macbooks.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
}
