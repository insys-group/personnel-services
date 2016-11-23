package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.Location;

/**
 * {@link LocationRepository} for PersonellServices.
 *
 * @author  Kris Krishna
 * @since 1.0.0
**/
@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location, Long> {

}
