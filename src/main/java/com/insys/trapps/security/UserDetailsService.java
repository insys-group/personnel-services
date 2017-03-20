package com.insys.trapps.security;

//import com.rd.domain.Authority;
//import com.rd.domain.User;
//import com.rd.repository.UserRepository;
import com.insys.trapps.model.security.User;
import com.insys.trapps.model.security.UserAuthority;
import com.insys.trapps.respositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;


@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        log.debug("Authenticating {}", login);

        User userFromDatabase = userRepository.findByUsername(login);

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (!userFromDatabase.isEnabled()) {
            throw new UserNotActivatedException("User " + login + " is not activated");
        }

        UserDetails ud = new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(), userFromDatabase.getPassword(), userFromDatabase.getAuthorities());;

        return ud;

    }

}
