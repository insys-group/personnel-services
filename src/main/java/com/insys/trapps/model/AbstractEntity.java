package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    @Column(name = "VERSION")
    private Long version = 1L;

}
