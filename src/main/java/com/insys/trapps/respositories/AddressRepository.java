package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Address;

/**
 * {@link Address Repository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}