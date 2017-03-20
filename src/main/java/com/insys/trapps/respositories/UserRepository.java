package com.insys.trapps.respositories;

import com.insys.trapps.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msabir on 2/15/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUsername(String username);

}
