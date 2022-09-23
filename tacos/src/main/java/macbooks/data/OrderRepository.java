package macbooks.data;

import org.springframework.data.repository.CrudRepository;

import macbooks.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
