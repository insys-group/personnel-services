package com.insys.trapps.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.insys.trapps.model.Business;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.service.BusinessService;

/**
 * {@link BusinessService Implementation} for PersoneelServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/
@Component("businessService")
@Transactional
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    public List<Business> listBusinesses() {
	List<Business> businessCollection = new ArrayList<>();
	businessRepository.findAll().forEach(businessCollection::add);
	return businessCollection;
    }

    public Business createBusiness(Business business) {
	Assert.notNull(business, "Business must not be null");
	Business savedBusiness = businessRepository.save(business);
	return savedBusiness;
    }

}
