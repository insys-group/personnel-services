package com.insys.trapps.respositories;

import com.insys.trapps.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/18/2016.
 */
@RepositoryRestResource
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByComments(String comments);
}
