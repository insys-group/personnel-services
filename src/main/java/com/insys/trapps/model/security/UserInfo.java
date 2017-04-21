package com.insys.trapps.model.security;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by msabir on 2/20/17.
 */
@EqualsAndHashCode(of = {"username"})
@ToString(of = {"id", "username", "firstName", "lastName", "email", "personType", "authorities"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String personType;
    private String username;
    private boolean passwordChanged;
    private Set<String> authorities=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
