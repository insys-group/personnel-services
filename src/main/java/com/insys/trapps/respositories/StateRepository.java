package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.State;

@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Long>{

}
