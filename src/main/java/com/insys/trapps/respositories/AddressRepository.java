package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Address;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * {@link Address Repository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/
@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}