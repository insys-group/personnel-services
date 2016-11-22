package com.insys.trapps.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.insys.trapps.model.Business;
import com.insys.trapps.model.Role;
import com.insys.trapps.respositories.BusinessRepository;
import com.insys.trapps.respositories.RoleRepository;
import com.insys.trapps.service.BusinessService;
import com.insys.trapps.service.RoleService;

@Component("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	public List<Role> listRoles() {
		return null;
	}

}
