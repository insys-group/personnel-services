package com.insys.trapps.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.insys.trapps.model.Role;
import com.insys.trapps.service.RoleService;

/**
 * {@link RoleController} for PersoneelServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> listRoles() {
		// return Arrays.asList(businessRepository.findAll());

		List<Role> list = roleService.listRoles();

		return new ResponseEntity<List<Role>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> createBusiness(@RequestBody Role request) throws Exception {

		Role role = roleService.createRole(request);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newRoleUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(role.getId()).toUri();
		responseHeaders.setLocation(newRoleUri);

		return new ResponseEntity<Role>(role, responseHeaders, HttpStatus.CREATED);

	}

}
