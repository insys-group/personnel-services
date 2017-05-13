/**
 * 
 */
package com.insys.trapps.model.person;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insys.trapps.model.Skill;
import lombok.*;

@Entity
@Table(name = "person_skill")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonSkill implements Serializable {

	private static final long serialVersionUID = -5990810947595710271L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @Getter
    @Setter
    @Column(name = "SCALE", nullable = false)
    private Integer scale;
}