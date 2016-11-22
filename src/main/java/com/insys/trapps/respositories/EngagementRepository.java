package com.insys.trapps.respositories;

import com.insys.trapps.model.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@RepositoryRestResource
public interface EngagementRepository extends JpaRepository<Engagement, Long> {
    List<Engagement> findByComments(String comments);
}
