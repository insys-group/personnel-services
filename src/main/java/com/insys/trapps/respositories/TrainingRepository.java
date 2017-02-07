package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.Training;

@RepositoryRestResource
public interface TrainingRepository extends JpaRepository<Training, Long> {

}