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

@Entity
@Table(name = "INTERVIEW")
@EqualsAndHashCode(of = { "candidate", "role", "date" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
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
	@Column(name = "DATE", nullable = false)
	private long date;

	@Getter
	@Setter
	@Column(name = "PHONE")
	private String phone;

	@Getter
	@Setter
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "PERSON_ID", nullable = false)
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
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "INTERVIEWERS", joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"))
	private Set<Person> interviewers = new HashSet<>();

	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "QUESTIONS", joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID"))
	private Set<Question> questions = new HashSet<>();

	@Getter
	@Setter
	@OneToOne
	@JoinTable(name = "INT_FEEDBACK", joinColumns = @JoinColumn(name = "INTERVIEW_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "FEEDBACK_ID", referencedColumnName = "ID"))
	private Feedback feedback;
}
