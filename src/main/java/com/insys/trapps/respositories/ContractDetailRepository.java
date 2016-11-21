package com.insys.trapps.respositories;

import com.insys.trapps.model.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/21/2016.
 */
@RepositoryRestResource
public interface ContractDetailRepository extends JpaRepository<ContractDetail, Long> {
    List<ContractDetail> findByComments(String comments);
}

