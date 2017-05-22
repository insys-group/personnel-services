package com.insys.trapps.model;

import com.fasterxml.jackson.annotation.*;

import com.insys.trapps.model.person.Person;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person_training")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PersonTraining implements Serializable {

    private static final long serialVersionUID = -5990810947595710271L;

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
    private boolean hided;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_training_task_completion"
            , joinColumns = @JoinColumn(name = "person_training_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "tasks_id", referencedColumnName = "id")
    )
    private Set<TrainingTask> completedTasks = new HashSet<>();

}