package com.insys.trapps.respositories;

import org.springframework.data.repository.CrudRepository;

import com.insys.trapps.model.Skill;

/**
 * @author Brad Starkenberg
 */
public interface SkillRepository extends CrudRepository<Skill, Long> {

}
