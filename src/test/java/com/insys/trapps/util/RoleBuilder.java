package com.insys.trapps.util;

import com.insys.trapps.model.Role;
import com.insys.trapps.model.Skill;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * Created by vnalitkin on 11/18/2016.
 */
public class RoleBuilder {
    private Role role = new Role();

    /*
    * This is a factory method for the Builder which builds the Role object
     * @param comments Creates the Role using the comments.
     * @return instance of RoleBuilder so that it can be chained.
     */
    public static RoleBuilder buildRole(String comments) {
        RoleBuilder builder = new RoleBuilder();
        builder.role.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the Role object
     * @param Role Creates the Role using existing object. It is used to add more stuff to the Role.
     * @return instance of RoleBuilder so that it can be chained.
     */
    public static RoleBuilder buildRole(Role role) {
        RoleBuilder builder = new RoleBuilder();
        builder.role = role;
        return builder;
    }

    public RoleBuilder name(String _name) {
        role.setName(_name);
        return this;
    }

    public RoleBuilder addSkill(Skill skill) {
        if (role.getSkills() == null) {
            role.setSkills(new HashSet<>());
        }
        role.getSkills().add(skill);
        return this;
    }

    /*
    * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public Role build() {
        return role;
    }

}
