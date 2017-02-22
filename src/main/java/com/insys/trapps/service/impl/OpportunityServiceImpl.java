package com.insys.trapps.service.impl;


import com.insys.trapps.model.Opportunity;
import com.insys.trapps.respositories.OpportunityRepository;
import com.insys.trapps.service.OpportunityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpportunityServiceImpl implements OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    private Logger logger = Logger.getLogger(OpportunityService.class);

    public Opportunity getOpportunity(long id) {
        return opportunityRepository.findOne(id);
    }

    public List<Opportunity> getOpportunities(){
        return opportunityRepository.findAll();
    }

}
