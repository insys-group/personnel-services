package com.insys.trapps.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "SKILL")
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends AbstractEntity {

    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

}
