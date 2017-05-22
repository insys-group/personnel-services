package com.insys.trapps.respositories;


import com.insys.trapps.model.person.PersonSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Repository
public interface PersonSkillRepository extends JpaRepository<PersonSkill, Long> {

}
