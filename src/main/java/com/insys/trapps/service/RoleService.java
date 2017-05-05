/**
 * 
 */
package com.insys.trapps.service;

import com.insys.trapps.model.Role;

/**
 * @author areyna
 *
 */
public interface RoleService {

	Role findOne(Long id);

	Role save(Role role);

}
