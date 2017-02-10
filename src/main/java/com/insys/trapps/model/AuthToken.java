/**
 * 
 */
package com.insys.trapps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @JsonProperty("access_token")
	private String accessToken;
	
	@Getter
	@Setter
    @JsonProperty("token_type")
	private String tokenType;
	
	@Getter
	@Setter
    @JsonProperty("expires_in")
	private Long expiresIn;
	
	private Date expiration;
	
	@Getter
	@Setter
    @JsonProperty("scope")
	//private Set<String> scopes;
    private String scope;
	
	@Getter
	@Setter
    @JsonProperty("refresh_token")
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
