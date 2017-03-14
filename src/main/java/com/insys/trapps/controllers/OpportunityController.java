package com.insys.trapps.controllers;

import com.insys.trapps.model.Opportunity;
import com.insys.trapps.service.OpportunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by areyna on 2/16/17.
 */
@RepositoryRestController
@RequestMapping("/api/v1")
public class OpportunityController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private OpportunityService opportunityService;

    @GetMapping(value = "/opportunities/{id}")
    public ResponseEntity<Opportunity> getOpportunity(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(opportunityService.getOpportunity(id));
    }

}
