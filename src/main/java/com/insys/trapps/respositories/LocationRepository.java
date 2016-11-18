package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Location;

/**
 * {@link LocationRepository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

}
