package com.insys.trapps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insys.trapps.service.TrainingService;

@RepositoryRestController
@RequestMapping("/api/v1")
public class TrainingController {
	
	@Autowired
	TrainingService service;
	
}
