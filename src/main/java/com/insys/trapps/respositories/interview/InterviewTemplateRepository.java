package com.insys.trapps.respositories.interview;

import com.insys.trapps.model.interview.InterviewTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InterviewTemplateRepository extends JpaRepository<InterviewTemplate, Long> {
}
