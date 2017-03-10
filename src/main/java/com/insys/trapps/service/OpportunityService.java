package com.insys.trapps.service;


import com.insys.trapps.model.Opportunity;

import java.util.List;

public interface OpportunityService {

    Opportunity getOpportunity(long id);

    List<Opportunity> getOpportunities();

}
