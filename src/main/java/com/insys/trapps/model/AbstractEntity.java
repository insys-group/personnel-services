package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter
    @Setter
    protected Long id;

    @Getter
    @Setter
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERSION")
    private Date version;

}
