package com.insys.trapps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insys.trapps.respositories.TrainingRepository;

@Service
public class TrainingServiceImpl implements TrainingService {
	
	@Autowired
	private TrainingRepository repository;

}
