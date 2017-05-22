package com.insys.trapps.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "engagement_opening")
@EqualsAndHashCode(of = {"engagement", "role", "address"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EngagementOpening implements Serializable {
	private static final long serialVersionUID = 8180153128817195196L;

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
    @ManyToOne
    @JoinColumn(name = "engagement_id")
    private Engagement engagement;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "rate")
    @Getter
    @Setter
    private BigDecimal rate;

    @Column(name = "comments")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @OneToMany(mappedBy = "engagementOpening", cascade = CascadeType.ALL)
    private Set<Contract> contracts;
	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}

}