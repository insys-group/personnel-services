package com.insys.trapps.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insys.trapps.model.Role;
import com.insys.trapps.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> listRoles() {

		List<Role> list = roleService.listRoles();

		return new ResponseEntity<List<Role>>(list, HttpStatus.OK);
	}
}
