package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "ROLE")
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_SKILL"
            , joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID")
    )
    private Set<Skill> skills;

    @Getter
    @Setter
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<EngagementOpening> engagementOpenings;

}
