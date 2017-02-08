/**
 * 
 */
package com.insys.trapps.controllers.security;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author msabir
 *
 */
@RestController
public class ResourceController {
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
