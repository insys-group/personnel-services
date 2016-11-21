package com.insys.trapps.respositories;


import com.insys.trapps.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@RepositoryRestResource
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByName(String name);
}
