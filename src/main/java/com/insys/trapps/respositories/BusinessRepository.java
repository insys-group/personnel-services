package com.insys.trapps.respositories;

import com.insys.trapps.model.Business;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * {@link BusinessRepository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/
@RepositoryRestResource(path = "businesses")
public interface BusinessRepository extends PagingAndSortingRepository<Business, Long> {
	// TODO
}
