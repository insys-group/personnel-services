package com.insys.trapps.model.security;

import com.insys.trapps.model.person.Person;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by msabir on 2/15/17.
 */
@Entity
@Table(name = "USER")
@EqualsAndHashCode(of = {"username"})
@ToString(of = {"username", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "authorities"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 3911995980240948127L;

    @Id
    @Getter
    @Setter
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "PERSON_ID", nullable = false)
    private Long personId;

    @Getter
    @Setter
    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    private boolean accountNonExpired=true;

    @Getter
    @Setter
    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    private boolean accountNonLocked=true;

    @Getter
    @Setter
    @Column(name = "CREDENTIALS_NON_EXPIRED", nullable = false)
    private boolean credentialsNonExpired=true;

    @Getter
    @Setter
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled=false;

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    private Set<UserAuthority> authorities=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.authorities.size()==0) {
            authorities.add(new UserAuthority(this, "USER"));
        }
        return authorities;
    }

    /*
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }*/
}
