package com.insys.trapps.respositories.interview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.interview.Interview;

@RepositoryRestResource
public interface InterviewRepository extends JpaRepository<Interview, Long> {
	Interview findByDate(long date);
	Interview findByCandidate(Person candidate);
	List<Interview> findByRole(Role role);
}
