package com.insys.trapps.model.interview;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

import com.insys.trapps.model.Person;

@Entity
@Table(name = "FEEDBACK")
@EqualsAndHashCode(of = { "interviewer", "comment" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback implements Serializable {
	private static final long serialVersionUID = -160410578502096831L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "PERSON_ID")
	private Person interviewer;

	@Getter
	@Setter
	@Column(nullable = false)
	private String comment;
}
