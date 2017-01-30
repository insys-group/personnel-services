package com.insys.trapps.model.interview;

import javax.persistence.*;

import com.insys.trapps.model.Person;
import com.insys.trapps.model.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	@JoinColumn(name = "PERSON_ID")
	private Person candidate;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private Role role;

	// @Version
	// @Getter
	// @Setter
	// @Column(name = "VERSION")
	// private Long version;

	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.MERGE},  orphanRemoval = true)
	@JoinTable(joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID", nullable = false), 
		inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false))
	private Set<Person> interviewers;

	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.ALL },  orphanRemoval = true)
	@JoinTable(joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"),
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
