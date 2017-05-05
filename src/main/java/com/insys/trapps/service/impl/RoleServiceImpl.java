package com.insys.trapps.service.impl;

import com.insys.trapps.model.Role;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.service.PersonService;
import com.insys.trapps.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author msabir
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private Logger logger=Logger.getLogger(PersonService.class);

    @Autowired
    private RoleRepository roleRepository;

    public Role findOne(Long id){
        return roleRepository.findOne(id);
    }

    public Role save(Role role){
        return roleRepository.saveAndFlush(role);
    }

}