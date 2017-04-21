package com.insys.trapps.controllers.security;

import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.security.Login;
import com.insys.trapps.model.security.User;
import com.insys.trapps.model.security.UserInfo;
import com.insys.trapps.service.MailSender;
import com.insys.trapps.service.PersonService;
import com.insys.trapps.service.UserService;
import com.insys.trapps.util.Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
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

    @Autowired
    private MailSender mailSender;

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
        userInfo.setPasswordChanged(user.isPasswordChanged());
        return userInfo;
    }

    @GetMapping(value = "/api/v1/user/person/{personId}")
    public ResponseEntity<User> getUserByPersonId(@PathVariable("personId") long personId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findOneByPersonId(personId));
    }

    @PostMapping(value = "/api/v1/user/create")
    @ResponseStatus(HttpStatus.OK)
    public User save(@RequestBody long personId) {

        Person person = personService.findPerson(personId);
        User user = new User();

        String username = person.getEmail();
        int index = username.indexOf("@");
        username = username.substring(0, index);

        String password = Util.generatePassword();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setAccountNonExpired(Boolean.TRUE);
        user.setAccountNonLocked(Boolean.TRUE);
        user.setCredentialsNonExpired(Boolean.TRUE);
        user.setEnabled(Boolean.TRUE);
        user.addAuthority("ADMIN");
        user.setPersonId(person.getId());

        user = userService.save(user);
        user.setPassword(password);
        mailSender.sendEmailNewAccount(user, person);

        return user;
    }

    @PostMapping(value = "/api/v1/changepassword")
    @ResponseStatus(HttpStatus.OK)
    public UserInfo changepassword(@RequestBody Login login) {

        String password = login.getPassword();
        User existingUser = userService.findOne(login.getUsername());

        existingUser.setPassword(passwordEncoder.encode(password));
        existingUser.setPasswordChanged(Boolean.TRUE);
        existingUser = userService.save(existingUser);

        Person person = personService.findPerson(existingUser.getPersonId());
        mailSender.sendEmailPasswordChanged(existingUser, person);

        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(existingUser.getUsername());
        userInfo.setAuthorities(existingUser.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()));
        userInfo.setId(person.getId());
        userInfo.setFirstName(person.getFirstName());
        userInfo.setLastName(person.getLastName());
        userInfo.setEmail(person.getEmail());
        userInfo.setPersonType(person.getPersonType().toString());
        userInfo.setPasswordChanged(existingUser.isPasswordChanged());

        return userInfo;
    }

    @PostMapping(value = "/api/resetpassword")
    @ResponseStatus(HttpStatus.OK)
    public boolean resetpassword(@RequestBody Login login) {

        User user = userService.findOne(login.getUsername());

        if(user != null){
            Person person = personService.findPerson(user.getPersonId());

            String password = Util.generatePassword();
            user.setPassword(passwordEncoder.encode(password));
            user.setPasswordChanged(Boolean.FALSE);
            userService.save(user);

            user.setPassword(password);
            mailSender.sendEmailPasswordReset(user, person);
        }

        return Boolean.TRUE;

    }

}
