package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Business;

public interface BusinessRepository extends PagingAndSortingRepository<Business, Long> {

    
}
