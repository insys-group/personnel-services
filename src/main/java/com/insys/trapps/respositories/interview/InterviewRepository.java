package com.insys.trapps.respositories.interview;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.interview.Interview;

@RepositoryRestResource
public interface InterviewRepository extends JpaRepository<Interview, Long> {
	List<Interview> findByDate(Date date);
	List<Interview> getAllInterviews();
}
