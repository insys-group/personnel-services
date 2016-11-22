package com.insys.trapps.service;

import java.util.List;

import com.insys.trapps.model.Role;

public interface RoleService {
	
	public List<Role> listRoles() ;
	
	public Role createRole(Role role) ;

}
