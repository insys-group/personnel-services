package com.insys.trapps.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.service.InterviewService;

@RepositoryRestController
@RequestMapping("/api/v1")
public class InterviewController {
	private Logger logger = LoggerFactory.getLogger(InterviewController.class);
	@Autowired
	private InterviewService service;
	
	@PutMapping(value = "/interviews/put/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateInterview(@PathVariable("id") Long id, @RequestBody Interview interview) {
		logger.debug("Updating Interview : " + id);
		service.updateInterview(id, interview);
	}
}
