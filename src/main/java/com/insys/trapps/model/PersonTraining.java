package com.insys.trapps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @Enumerated(EnumType.STRING)
    @Column(length = 12, columnDefinition = "varchar(12) default 'NOT_STARTED'")
    private ProgressType progress = ProgressType.NOT_STARTED;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    @JsonBackReference ("person-trainings")
    private Person person;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRAINING_ID")
	@JsonBackReference("assign-training")
    private Training training;

}