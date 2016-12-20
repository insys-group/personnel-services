package com.insys.trapps.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.insys.trapps.model.PersonSkill;

/**
 * @author msabir
 *
 */
@RepositoryRestResource(path="/personskills", collectionResourceRel="personskills")
public interface PersonSkillRepository extends JpaRepository<PersonSkill, Long> {

}