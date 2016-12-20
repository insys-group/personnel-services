/**
 * 
 */
package com.insys.trapps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class PersonSkill extends AbstractEntity {
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
}