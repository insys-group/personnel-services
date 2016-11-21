package com.insys.trapps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Brad Starkenberg
 */
@Entity
public class PersonSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@ManyToOne
	@JsonIgnore
	private Person person;
	@NotNull
	@ManyToOne
	private Skill skill;
	@NotNull
	private int level;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}
	/**
	 * @param skill the skill to set
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return person.hashCode() * skill.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		PersonSkill rhs = (PersonSkill) obj;
		return (this.getPerson().equals(rhs.getPerson()) && this.getSkill().equals(rhs.getSkill()));
	}

}
