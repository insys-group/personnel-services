package com.insys.trapps.respositories;

import com.insys.trapps.model.EngagementOpening;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@RepositoryRestResource
public interface EngagementOpeningRepository extends CrudRepository<EngagementOpening, Long> {

}
