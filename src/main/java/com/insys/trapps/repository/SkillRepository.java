package com.insys.trapps.repository;

import com.insys.trapps.model.Role;
import com.insys.trapps.model.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@RepositoryRestResource
public interface SkillRepository extends CrudRepository<Skill, Long> {
    List<Skill> findByName(String name);
}
