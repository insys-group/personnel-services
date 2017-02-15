package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insys.trapps.model.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

}