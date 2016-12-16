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

import java.util.Iterator;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by vnalitkin on 12/16/16.
 */

@RepositoryRestController
@RequestMapping(value = "/api/v1/businesses/put/1", method = PUT)
public class BusinesPutControler {

    @Autowired
    private BusinessRepository businessRepository;

    @RequestMapping
    public @ResponseBody Resource<Business> businessesPut(@RequestBody Business business) {
        Business businessOld = businessRepository.findOne(business.getId());
        Iterator<Address> iterator = businessOld.getAddresses().iterator();
        while (iterator.hasNext()) {
            if (!business.getAddresses().contains(iterator.next())) {
                iterator.remove();
            }
        }
        iterator = business.getAddresses().iterator();
        while (iterator.hasNext()) {
            Address item = iterator.next();
            if (!businessOld.getAddresses().contains(item)) {
                businessOld.getAddresses().add(item);
            }
        }

        businessRepository.save(businessOld);
        businessOld = businessRepository.findOne(business.getId());
        return new Resource<Business>(businessOld);
    }
}
