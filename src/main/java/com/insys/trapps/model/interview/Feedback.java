package com.insys.trapps.model.interview;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insys.trapps.model.AbstractEntity;
import com.insys.trapps.model.Person;

@Entity
@Table(name = "FEEDBACK")
@EqualsAndHashCode(of = {"interviewer", "comment"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback extends AbstractEntity {
	@Getter
    @Setter
	@ManyToOne
    @JoinColumn(name = "PERSON_ID")
	private Person interviewer;
	
	@Getter
	@Setter
    @Column(name = "COMMENT", nullable = false)
	private String comment;
}
