package com.insys.trapps.respositories;

import com.insys.trapps.model.BusinessEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * {@link BusinessRepository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/
@RepositoryRestResource
public interface BusinessRepository extends PagingAndSortingRepository<BusinessEntity, Long> {
	// TODO
}
