package com.insys.trapps.respositories;

import com.insys.trapps.model.Engagement;
import com.insys.trapps.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByName(String name);
}
