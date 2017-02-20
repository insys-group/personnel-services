package com.insys.trapps.service.security;

import com.insys.trapps.model.security.User;
import com.insys.trapps.respositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by msabir on 2/15/17.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Enter: UserDetailsServiceImpl.loadUserByUsername" + username);

        User user=userRepository.getOne(username);
        if(user==null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        } else {
            logger.debug("User loaded " + user.toString());
        }
        return user;
    }
}