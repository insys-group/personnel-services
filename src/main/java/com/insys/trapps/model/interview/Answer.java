package com.insys.trapps.model.interview;

import lombok.*;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by areyna on 2/20/17.
 */

@Entity
@Table(name = "ANSWER")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Answer implements Serializable {

    private static final long serialVersionUID = -187204269892201461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @OneToOne(targetEntity = Question.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

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
