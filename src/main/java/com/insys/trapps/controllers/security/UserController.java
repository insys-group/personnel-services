package com.insys.trapps.controllers.security;

import com.insys.trapps.model.person.Person;
import com.insys.trapps.model.security.User;
import com.insys.trapps.model.security.UserInfo;
import com.insys.trapps.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.stream.Collectors;

/**
 * Created by msabir on 2/20/17.
 */
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PersonService personService;

    public UserController(PersonService personService) {
        this.personService=personService;
    }

    @GetMapping("/user")
    public UserInfo user(Principal user) {
        logger.debug("Enter: UserController.user()" + user.getName());
        return loadUserInfo(user.getName());
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
}
