/**
 * 
 */
package com.insys.trapps.model.security;

import lombok.*;

import java.io.Serializable;

/**
 * @author msabir
 *
 */
@EqualsAndHashCode(of = {"username"})
@NoArgsConstructor
@ToString
public class PasswordCredentials implements Serializable {
	private static final long serialVersionUID = -3441844237908020780L;

	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;
}
