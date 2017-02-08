/**
 * 
 */
package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author msabir
 *
 */
@EqualsAndHashCode(of = {"accessToken"}, callSuper = false)
@NoArgsConstructor
@ToString
public class AuthToken implements Serializable {
	private static final long serialVersionUID = -4802349035116419096L;
	
	@Getter
	@Setter
	private String accessToken;
	
	@Getter
	@Setter
	private String tokenType;
	
	@Getter
	@Setter
	private Long expiresIn;
	
	private Date expiration;
	
	@Getter
	@Setter
	private Set<String> scopes;
	
	@Getter
	@Setter
	private String refreshToken;
	
	public void setExpiration(Date expiration) {
		this.expiration=new Date(expiration.getTime());
	}
	
	public Date getExpiration() {
		if(expiration!=null)
			return new Date(expiration.getTime());
		return null;
	}
}
