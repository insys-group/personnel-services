package com.insys.trapps.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.Business;

/**
 * {@link BusinessRepository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/
@RepositoryRestResource
public interface BusinessRepository extends JpaRepository<Business, Long> {
	// TODO
}
