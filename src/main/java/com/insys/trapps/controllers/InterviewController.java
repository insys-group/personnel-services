package com.insys.trapps.controllers;

import com.insys.trapps.model.interview.Feedback;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.service.InterviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by areyna on 2/16/17.
 */
@RepositoryRestController
@RequestMapping("/api/v1")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping(value = "/interview/{id}")
    public ResponseEntity<Interview> getInterview(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(interviewService.getInterview(id));
    }

    @PutMapping(value = "/feedback")
    @ResponseStatus(HttpStatus.OK)
    public void updateFeedback(@RequestBody Feedback feedback) {
        interviewService.updateFeedback(feedback);
    }

}
