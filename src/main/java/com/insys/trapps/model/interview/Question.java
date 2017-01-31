package com.insys.trapps.model.interview;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "QUESTION")
@EqualsAndHashCode(of = { "question" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question implements Serializable {
	private static final long serialVersionUID = -3773883069993102682L;

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
    @JoinColumn(name = "INTERVIEW_ID")
    private Interview interview;

	@Getter
	@Setter
	@Column(name = "QUESTION", nullable = false)
	private String question;

	@Getter
	@Setter
	@Column(name = "ANSWER")
	private String answer;

	@Getter
	@Setter
	@Column(name = "COMMENT")
	private String comment;

	@Getter
	@Setter
	@Column(name = "QUALITY")
	@Enumerated(EnumType.STRING)
	private Quality quality;
}
