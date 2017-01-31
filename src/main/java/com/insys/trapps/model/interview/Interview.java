package com.insys.trapps.model.interview;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode(of = { "candidate", "role", "date" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Interview implements Serializable {
	private static final long serialVersionUID = 898931233669153755L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@Column(nullable = false)
	private long date;

	@Getter
	@Setter
	private String phone;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	private Person candidate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false)
	private Role role;


	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.MERGE},  orphanRemoval = true)
	@JoinTable(name = "INTERVIEWERS",
			joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID", nullable = false), 
		inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false))
	private Set<Person> interviewers;

	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.ALL },  orphanRemoval = true)
	@JoinTable(name = "QUESTIONS",
			joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"),
		inverseJoinColumns = @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID"))
	private Set<Question> questions;

	@Getter
	@Setter
	@OneToOne
	@JoinTable(name = "INT_FEEDBACK", 
		joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "FEEDBACK_ID", referencedColumnName = "ID"))
	private Feedback feedback;
}
