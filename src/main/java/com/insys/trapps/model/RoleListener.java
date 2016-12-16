package com.insys.trapps.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.activation.DataSource;
import javax.persistence.PrePersist;

/**
 * Created by vnalitkin on 12/15/16.
 */
public class RoleListener {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PrePersist
    public void rolePrePersist(Role role) {
        System.out.println("Listening User Pre Persist : " + role.getName());
      //  List res = jdbcTemplate.queryForList("select * from role_skill");
     //   repository.deleteSkills(role.getId());
    }

}
