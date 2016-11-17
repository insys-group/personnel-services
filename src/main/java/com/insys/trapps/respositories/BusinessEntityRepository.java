package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.BusinessEntity;

public interface BusinessEntityRepository extends PagingAndSortingRepository<BusinessEntity, Long> {

    
}
