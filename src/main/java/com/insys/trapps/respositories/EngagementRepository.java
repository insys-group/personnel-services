package com.insys.trapps.respositories;

import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.Opportunity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@RepositoryRestResource
public interface EngagementRepository extends CrudRepository<Engagement, Long> {
    List<Engagement> findByComments(String comments);
}
