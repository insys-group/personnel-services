package com.insys.trapps.repository;

import com.insys.trapps.model.Contract;
import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.Opportunity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/18/2016.
 */
@RepositoryRestResource
public interface ContractRepository extends CrudRepository<Contract, Long> {
    List<Contract> findByComments(String comments);
}
