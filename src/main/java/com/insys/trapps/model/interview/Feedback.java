package com.insys.trapps.model.interview;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.insys.trapps.model.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FEEDBACK")
@EqualsAndHashCode(of = {"interviewer", "feedback"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {
	@Getter
	@Setter
    @JoinColumn(name = "PERSON_ID")
	private Person interviewer;
	
	@Getter
	@Setter
    @Column(name = "FEEDBACK", nullable = false)
	private String feedback;
}
