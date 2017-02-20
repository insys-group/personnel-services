package com.insys.trapps.respositories;

import com.insys.trapps.model.security.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msabir on 2/16/17.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    List<Client> findByClientId(String clientId);
}
