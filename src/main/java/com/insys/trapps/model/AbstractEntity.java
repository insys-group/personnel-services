package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString(exclude = {"id","version"})
@EqualsAndHashCode(exclude = {"id","version"})
@AllArgsConstructor
@NoArgsConstructor
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
