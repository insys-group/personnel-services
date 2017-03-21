package com.insys.trapps.respositories;

import com.insys.trapps.model.security.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by msabir on 2/15/17.
 */
@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String> {

}
