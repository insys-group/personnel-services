package com.insys.trapps.controllers.security;

import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.security.User;
import com.insys.trapps.model.security.UserInfo;
import com.insys.trapps.service.PersonService;
import com.insys.trapps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.stream.Collectors;

/**
 * Created by msabir on 2/20/17.
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public UserController(PersonService personService) {
        this.personService=personService;
    }

    @GetMapping(value = "/api/v1/user/{username}")
    public UserInfo user(@PathVariable("username") String username) {
        return loadUserInfo(username);
    }

    private UserInfo loadUserInfo(String username) {
        logger.debug("Enter: UserController.loadUserInfo" + username);
        UserInfo userInfo=new UserInfo();
        User user=personService.findUser(username);
        Person person=personService.findPerson(user.getPersonId());
        userInfo.setUsername(username);
        userInfo.setAuthorities(user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()));
        userInfo.setId(person.getId());
        userInfo.setFirstName(person.getFirstName());
        userInfo.setLastName(person.getLastName());
        userInfo.setEmail(person.getEmail());
        userInfo.setPersonType(person.getPersonType().toString());
        return userInfo;
    }

    @PostMapping(value = "/api/v1/newUser")
    @ResponseStatus(HttpStatus.OK)
    public User save(@RequestBody User user) {

        User existingUser = userService.findOne(user.getUsername());

        if(existingUser == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAccountNonExpired(Boolean.TRUE);
            user.setAccountNonLocked(Boolean.TRUE);
            user.setCredentialsNonExpired(Boolean.TRUE);
            user.setEnabled(Boolean.TRUE);
            user.addAuthority("ADMIN");
            user = userService.save(user);
            user.setAuthorities(null);
        }else {
            user.setUsername(null);
        }

        return user;
    }

    @PostMapping(value = "/api/v1/register")
    @ResponseStatus(HttpStatus.OK)
    public User register(@RequestBody User user) {

        User existingUser = userService.findOne(user.getUsername());

        if(existingUser == null){

            Person person = personService.findByEmail(user.getUsername());

            if(person == null){
                return null;
            } else {

                int index = user.getUsername().indexOf("@");
                user.setUsername(user.getUsername().substring(0, index));
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setAccountNonExpired(Boolean.TRUE);
                user.setAccountNonLocked(Boolean.TRUE);
                user.setCredentialsNonExpired(Boolean.TRUE);
                user.setEnabled(Boolean.TRUE);
                user.addAuthority("ADMIN");
                user.setPersonId(person.getId());
                user = userService.save(user);
                user.setAuthorities(null);

            }

        }else {
            user.setUsername(null);
        }

        return user;
    }

}
