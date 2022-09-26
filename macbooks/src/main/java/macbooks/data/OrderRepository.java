package macbooks.data;

import macbooks.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import macbooks.Order;

import java.util.List;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
}
