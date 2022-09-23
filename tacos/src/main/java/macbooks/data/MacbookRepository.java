package macbooks.data;

import org.springframework.data.repository.CrudRepository;

import macbooks.Macbook;

public interface MacbookRepository
         extends CrudRepository<Macbook, Long> {

}
