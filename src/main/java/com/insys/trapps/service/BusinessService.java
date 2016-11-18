package com.insys.trapps.service;

import java.util.List;

import com.insys.trapps.model.Business;

public interface BusinessService {
	
	List<Business>  listBusinesses();
	
	Business createBusiness(Business business);

}
