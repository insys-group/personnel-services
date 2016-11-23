package com.insys.trapps.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.Address;

/**
 * {@link Address Repository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/
@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Long> {

}