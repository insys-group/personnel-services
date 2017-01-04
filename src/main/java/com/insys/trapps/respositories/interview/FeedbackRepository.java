package com.insys.trapps.respositories.interview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.interview.Feedback;

@RepositoryRestResource
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
