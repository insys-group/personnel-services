package com.insys.trapps.model.interview;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
@EqualsAndHashCode(of = {"question"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
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
