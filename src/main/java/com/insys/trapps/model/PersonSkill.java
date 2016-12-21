/**
 * 
 */
package com.insys.trapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author msabir
 *
 */
@Entity
@Table(name = "PERSON_SKILL")
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonSkill {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Version
    @NonNull
    @Getter
    @Setter
    @Column(name = "VERSION")
    private Long version;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Getter
    @Setter
    @Column(name = "SCALE", nullable = false)
    private Integer scale;

	@Override
	public String toString() {
		return "PersonSkill [person=" + person + ", name=" + name + ", scale=" + scale + ", getId()=" + getId()
				+ ", getVersion()=" + getVersion() + "]";
	}
}