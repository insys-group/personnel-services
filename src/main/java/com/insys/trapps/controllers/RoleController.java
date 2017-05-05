/**
 *
 */
package com.insys.trapps.controllers;

import com.insys.trapps.model.Role;
import com.insys.trapps.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author msabir
 */
@RepositoryRestController
@RequestMapping("/api/v1")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/role/skill")
    public ResponseEntity<Role> assignSkill(@RequestBody Role role) throws Exception {
        try {
            Role existingRole = roleService.findOne(role.getId());
            existingRole.setSkills(role.getSkills());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(roleService.save(existingRole));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}