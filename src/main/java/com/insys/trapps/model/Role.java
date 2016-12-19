package com.insys.trapps.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "ROLE")
@EqualsAndHashCode(exclude = {"engagementOpenings"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @NonNull
    @Column(name = "SKILL", nullable = false)
    private String skill;

    @Getter
    @Setter
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<EngagementOpening> engagementOpenings;

}
