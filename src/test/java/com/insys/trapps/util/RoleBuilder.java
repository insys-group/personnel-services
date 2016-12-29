package com.insys.trapps.util;

import com.insys.trapps.model.Contract;
import com.insys.trapps.model.Role;
import com.insys.trapps.model.Skill;

import java.util.*;

/**
 * Created by vnalitkin on 11/21/2016.
 */
public class RoleBuilder {
    private Role role;

    public static RoleBuilder buildRole(String name) {
        RoleBuilder builder = new RoleBuilder();
        builder.role = Role.builder()
                .name(name)
                .version(1L)
                .build();
        builder.role.setSkills(
                new HashSet<>(
                        Arrays.asList(
                                Skill.builder().name("Skill 1 " + builder.role.getName()).version(1L).build()
                                , Skill.builder().name("Skill 2" + builder.role.getName()).version(1L).build()
                                , Skill.builder().name("Skill 3" + builder.role.getName()).version(1L).build()
                        )
                )
        );
        return builder;
    }

    public Role build() {
        return role;
    }

}
