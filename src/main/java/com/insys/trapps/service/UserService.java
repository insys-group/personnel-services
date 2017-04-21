package com.insys.trapps.service;

import com.insys.trapps.model.security.User;

/**
 * Created by areyna on 3/23/17.
 */
public interface UserService {

    User save(User user);

    User findOne(String username);

    User findOneByPersonId(long personId);

}
