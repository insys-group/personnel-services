package com.insys.trapps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSON_TRAINING")
@EqualsAndHashCode(of = {"training", "person"}, callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@ToString
public class PersonTraining implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private long startDate;

    @Getter
    @Setter
    @Column(nullable = false)
    private long endDate;

    @Getter
    @Setter
    private ProgressType progress;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    @JsonBackReference (value="person-trainings")
    private Person person;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "TRAINING_ID")
    @JsonBackReference
    private Training training;

}