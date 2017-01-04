package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import lombok.Setter;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "BUSINESS")
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Business implements Serializable {
	private static final long serialVersionUID = 542002485451980387L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
//
//    @Version
//    @Setter
//    @Getter
//    @Column(name = "VERSION")
//    private Long version;

    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Getter
    @Setter
    @Column(name = "BUSINESS_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Setter
    @Getter
    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private Set<Person> persons;

    @Getter
    @Setter
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Opportunity> opportunities;

    @Getter
    @Setter
    @OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "LOCATION"
            , joinColumns = @JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    )
    private Set<Address> addresses;

	@Override
	public String toString() {
		return "Business [name=" + name + ", description=" + description + ", businessType=" + businessType
				+ ", persons=" + persons + ", opportunities=" + opportunities + ", addresses=" + addresses
				+ ", getId()=" + getId() + "]";
	}
	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}
}
