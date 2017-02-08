/**
 * 
 */
package com.insys.trapps.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
