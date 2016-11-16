package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Location;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    
}
