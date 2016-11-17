package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
}
