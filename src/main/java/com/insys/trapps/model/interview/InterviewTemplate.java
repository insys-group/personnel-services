package com.insys.trapps.model.interview;

import com.insys.trapps.model.Role;
import lombok.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by areyna on 2/20/17.
 */

@Entity
@Table(name = "INTERVIEW_TEMPLATE")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
public class InterviewTemplate {

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
    @OneToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Getter
    @Setter
    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Question> questions;

}
