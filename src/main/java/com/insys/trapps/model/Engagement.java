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
import javax.persistence.ManyToOne;
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

@Entity
@Table(name = "ENGAGEMENT")
@EqualsAndHashCode(exclude = {"engagementOpenings"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Engagement implements Serializable {
	private static final long serialVersionUID = -7469162211507730481L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @OneToMany(mappedBy = "engagement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EngagementOpening> engagementOpenings;

}