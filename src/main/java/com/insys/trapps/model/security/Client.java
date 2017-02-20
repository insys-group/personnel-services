package com.insys.trapps.model.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by msabir on 2/16/17.
 */
@Entity
@Table(name = "Client")
@EqualsAndHashCode(of = {"clientId"})
@ToString(of = {"clientId", "secretRequired", "clientSecret", "scoped", "scopes", "grantTypes", "accessTokenValiditySeconds", "refreshTokenValiditySeconds", "autoApprove"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client implements ClientDetails {
    @Id
    @Getter
    @Setter
    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;

    @Getter
    @Setter
    @Column(name = "SECRET_REQUIRED", nullable = false)
    private boolean secretRequired;

    @Getter
    @Setter
    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @Getter
    @Setter
    @Column(name = "SCOPED", nullable = false)
    private boolean scoped;

    @Getter
    @Setter
    @Column(name = "SCOPES", nullable = false)
    private String scopes;

    @Getter
    @Setter
    @Column(name = "GRANT_TYPES", nullable = false)
    private String grantTypes;

    @Getter
    @Setter
    @Column(name = "ACCESS_TOKEN_VALIDITY_SECONDS", nullable = false)
    private Integer accessTokenValiditySeconds;

    @Getter
    @Setter
    @Column(name = "REFRESH_TOKEN_VALIDITY_SECONDS", nullable = false)
    private Integer refreshTokenValiditySeconds;

    @Getter
    @Setter
    @Column(name = "AUTO_APPROVE", nullable = false)
    private boolean autoApprove;

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public Set<String> getScope() {
        return Arrays.stream(scopes.split(",")).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return Arrays.stream(grantTypes.split(",")).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }
}
