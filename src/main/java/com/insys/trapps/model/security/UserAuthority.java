package com.insys.trapps.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by msabir on 2/15/17.
 */
@Entity
@Table(name = "USER_AUTHORITY")
@EqualsAndHashCode(of = {"user", "authority"})
@ToString(of = {"authority"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    public UserAuthority(User user, String authority) {
        this.user=user;
        this.authority=authority;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "USERNAME", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "AUTHORITY", nullable = false)
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
