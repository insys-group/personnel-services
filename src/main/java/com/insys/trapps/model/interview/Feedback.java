package com.insys.trapps.model.interview;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	// @Version
	// @Getter
	// @Setter
	// @Column(name = "VERSION")
	// private Long version;

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
