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
    private RoleRepository roleRepository;
    @Autowired
    private BusinessRepository businessRepository;

  /*  @RequestMapping(method = GET)
    String add() {
        LocalDateTime timePoint = LocalDateTime.now();
        Role role = roleRepository.findByName("Developer").get(0);
        role.getSkills().add(Skill.builder().name("Eser" + timePoint.toString()).build());
        roleRepository.save(role);
        role = roleRepository.findByName("Developer").get(0);
        return "";
    }*/

    /*
        @RequestMapping(path="/del" ,method = RequestMethod.GET)
        String del() {
            Role role = roleRepository.findByName("Developer").get(0);
            Iterator<Skill> iterator = role.getSkills().iterator();
            iterator.remove();
            roleRepository.save(role);
            role = roleRepository.findByName("Developer").get(0);
            return "";
        }

        @RequestMapping(path="/roles/put/" ,method = RequestMethod.PUT)
        String del(@RequestBody Role role) {
            Role roleOld = roleRepository.findByName(role.getName()).get(0);
            Iterator<Skill> iterator = role.getSkills().iterator();
            while (iterator.hasNext()){
                Skill skill = iterator.next();
                if(!roleOld.getSkills().contains(skill)){
                    roleOld.getSkills().add(skill);
                };
            }
            roleRepository.save(roleOld);
            roleOld = roleRepository.findByName(role.getName()).get(0);
            return "";
        }
      */

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
