package com.insys.trapps.model.interview;

import com.insys.trapps.model.person.Person;
import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
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
	@JoinColumn(name = "person_id")
	private Person interviewer;

	@Getter
	@Setter
	@Column(nullable = false)
	private String comment;
}
