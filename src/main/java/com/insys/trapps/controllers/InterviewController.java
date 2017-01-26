package com.insys.trapps.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.service.InterviewService;

@RepositoryRestController
@RequestMapping("/api/v1")
public class InterviewController {
	private Logger logger = LoggerFactory.getLogger(PersonController.class);
	@Autowired
	private InterviewService service;
	
	@PatchMapping(value = "/interviews/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void patchInterview(@PathVariable("id") Long id, @RequestBody Map<String, Object> patchVals) {
		service.patchInterview(id, patchVals);
	}
	
	@GetMapping(value = "/interviews/{id}") 
	@ResponseStatus(HttpStatus.OK)
	public Interview getInterview(@PathVariable("id") Long id) {
		return service.getInterview(id);
	}
}
