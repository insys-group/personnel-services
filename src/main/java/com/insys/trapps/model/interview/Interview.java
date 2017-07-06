package com.insys.trapps.model.interview;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.insys.trapps.model.Role;

import com.insys.trapps.model.person.Person;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "interview")
@EqualsAndHashCode(of = { "candidate", "role", "date" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
public class Interview implements Serializable {

	private static final long serialVersionUID = 898931233669153755L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	@Column(nullable = false)
	private Date date;

	@Getter
	@Setter
	@Column(name = "contact_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ContactType contactType;

	@Getter
	@Setter
	private String contact;

	@Getter
	@Setter
	private int status;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "person_id")
	@JsonManagedReference
	private Person candidate;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Getter
	@Setter
	@ManyToMany(targetEntity = Person.class, cascade = CascadeType.PERSIST)
	private Set<Person> interviewers;

	@Getter
	@Setter
	@OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL, orphanRemoval=true)
	@OrderBy("question_id ASC")
	private Set<Answer> answers;

	@Getter
	@Setter
	@OneToMany(targetEntity = Feedback.class, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Feedback> feedbacks;

}
