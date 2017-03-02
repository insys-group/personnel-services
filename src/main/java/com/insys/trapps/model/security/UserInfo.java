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
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String personType;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private Set<String> authorities=new HashSet<>();
}
