package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "SKILL")
@EqualsAndHashCode(of = "name", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Version
    @NonNull
    @Getter
    @Setter
    @Column(name = "VERSION")
    private Long version;

    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

}
