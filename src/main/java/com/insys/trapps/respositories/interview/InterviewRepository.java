package com.insys.trapps.respositories.interview;

import com.insys.trapps.model.interview.InterviewInlineProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.insys.trapps.model.interview.Interview;

@RepositoryRestResource(excerptProjection = InterviewInlineProjection.class)
public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
