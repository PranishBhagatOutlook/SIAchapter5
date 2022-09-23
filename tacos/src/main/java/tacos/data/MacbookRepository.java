package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Macbook;

public interface MacbookRepository
         extends CrudRepository<Macbook, Long> {

}
