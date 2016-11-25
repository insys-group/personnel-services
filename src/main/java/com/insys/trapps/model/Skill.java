package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "SKILL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

}
