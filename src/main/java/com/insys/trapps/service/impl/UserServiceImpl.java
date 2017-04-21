package com.insys.trapps.service.impl;

import com.insys.trapps.model.security.User;
import com.insys.trapps.respositories.UserRepository;
import com.insys.trapps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by areyna on 3/23/17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.saveAndFlush(user);
    }

    public User findOne(String username){
        return userRepository.findOne(username);
    }

    public User findOneByPersonId(long personId){
        return userRepository.findByPersonId(personId);
    }

}
