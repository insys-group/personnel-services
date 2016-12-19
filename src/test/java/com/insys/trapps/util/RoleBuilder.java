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

    public static RoleBuilder buildRole(String name, String skill) {
        RoleBuilder builder = new RoleBuilder();
		builder.role = Role.builder()
                .name(name)
                .skill(skill)
                .build();

        return builder;
    }

    public Role build() {
        return role;
    }

}
