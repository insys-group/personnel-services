package com.insys.trapps.model.interview;

import lombok.*;

import javax.persistence.*;

import com.insys.trapps.model.AbstractEntity;
import com.insys.trapps.model.Person;
import com.insys.trapps.model.Role;

import java.util.Set;

@Entity
@Table(name = "INTERVIEW")
@EqualsAndHashCode(of = { "candidate", "role", "date" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interview extends AbstractEntity {
	@Getter
	@Setter
	@Column(name = "DATE", nullable = false)
	private long date;

	@Getter
	@Setter
	@Column(name = "PHONE", nullable = false)
	private String phone;
	
	@Getter
	@Setter
	@JoinColumn(name = "PERSON_ID", nullable = false)
	private Person candidate;

	@Getter
	@Setter
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private Role role;

	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "INTERVIEWERS", 
		joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	)
	private Set<Person> interviewers;

	@Getter
	@Setter
	@Column(name = "QUESTIONS")

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "QUESTIONS", 
		joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
	)
	private Set<Question> questions;

	@Getter
	@Setter
	@OneToOne
	@JoinTable(name = "INT_FEEDBACK", 
		joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "FEEDBACK_ID", referencedColumnName = "ID")
	)
	private Feedback feedback;
}
