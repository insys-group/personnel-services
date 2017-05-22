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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.insys.trapps.model.person.Person;
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
@Table(name = "business")
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Business implements Serializable {
	private static final long serialVersionUID = 542002485451980387L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "description", nullable = false)
    private String description;

    @Getter
    @Setter
    @Column(name = "business_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

//    @Setter
//    @Getter
//    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
//     @JoinTable(joinColumns = @JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID"),
//         inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"))
//     private Set<Person> persons;

    @Getter
    @Setter
    @OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "location"
            , joinColumns = @JoinColumn(name = "business_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id")
    )
    private Set<Address> addresses;

	@Override
	public String toString() {
		return "Business [name=" + name + ", description=" + description + ", businessType=" + businessType
				+ ", addresses=" + addresses
				+ ", getId()=" + getId() + "]";
	}
}
