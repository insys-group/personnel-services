package com.insys.trapps.controllers;

import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by vnalitkin on 12/16/16.
 */

@RepositoryRestController
@RequestMapping(value = "/api/v1/businesses/put/1", method = PUT)
public class BusinesPutControler {

    @Autowired
    private BusinessRepository repository;

    @RequestMapping
    public @ResponseBody Resource<Business> businessesPut(@RequestBody Business business) {
        //Business businessOld = repository.findOne(business.getId());
        Set<Address> addressSet = new HashSet<>();
        business.getAddresses().forEach(addressSet::add);
        business.getAddresses().clear();
        //business.setVersion(businessOld.getVersion());
        repository.save(business);
        business = repository.findOne(business.getId());
        addressSet.forEach(business.getAddresses()::add);
        repository.save(business);
        return new Resource<Business>(business);
    }
}
