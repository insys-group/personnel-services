/**
 * 
 */
package com.insys.trapps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

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
    @JsonProperty("scope")
	//private Set<String> scopes;
    private String scope;
	
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

	@Getter
	@Setter
	private String error;

	@Getter
	@Setter
	private String errorDescription;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String firstName;

	@Getter
	@Setter
	private String lastName;

	@Getter
	@Setter
	private String authorities;
}
