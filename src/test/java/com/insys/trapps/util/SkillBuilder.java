package com.insys.trapps.util;

import com.insys.trapps.model.Skill;

/**
 * Created by vnalitkin on 11/18/2016.
 */
public class SkillBuilder {
    private Skill skill = new Skill();

    /*
    * This is a factory method for the Builder which builds the Skill object
     * @param comments Creates the Skill using the comments.
     * @return instance of SkillBuilder so that it can be chained.
     */
    public static SkillBuilder buildSkill(String comments) {
        SkillBuilder builder = new SkillBuilder();
        builder.skill.setComments(comments);
        return builder;
    }

    /*
     * This is a factory method for the Builder which builds the Skill object
     * @param Skill Creates the Skill using existing object. It is used to add more stuff to the Skill.
     * @return instance of SkillBuilder so that it can be chained.
     */
    public static SkillBuilder buildSkill(Skill skill) {
        SkillBuilder builder = new SkillBuilder();
        builder.skill = skill;
        return builder;
    }

    public SkillBuilder name(String _name) {
        skill.setName(_name);
        return this;
    }

    /*
    * This method returns the constructed Opportunity instance.
     * @return Opportunity Constructed Opportunity instance.
     */
    public Skill build() {
        return skill;
    }
}
