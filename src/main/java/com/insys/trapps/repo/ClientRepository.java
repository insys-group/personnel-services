package com.insys.trapps.repo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


import com.insys.trapps.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
		List<Client> findByName(String name);
}
