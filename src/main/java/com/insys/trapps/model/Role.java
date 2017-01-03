package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "ROLE")
@EqualsAndHashCode(exclude = {"skills", "engagementOpenings"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {
	private static final long serialVersionUID = -4909776048559424643L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

//    @Version
//    @Getter
//    @Setter
//    @Column(name = "VERSION")
//    private Long version;

    @Getter
    @Setter
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval=true)
    @JoinTable(name = "ROLE_SKILL"
            , joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "SKILL_ID", referencedColumnName = "ID")
    )
    private Set<Skill> skills;

    @Getter
    @Setter
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<EngagementOpening> engagementOpenings;
	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}

}
