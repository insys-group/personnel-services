package com.insys.trapps.model.person;

import com.insys.trapps.model.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(of = {"email"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {
	private static final long serialVersionUID = 7055994680040943127L;

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
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "PHONE")
    private String phone;

    @Getter
    @Setter
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "TITLE", nullable = true)
    private String title;

    @Getter
    @Setter
    @Column(name = "PERSON_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", nullable=true, insertable=true, updatable=true)
    private Address address;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "BUSINESS_ID", nullable = false)
    private Business business;

    @Getter
    @Setter
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private Set<PersonDocument> personDocuments=new HashSet<>();
    
    @Getter
    @Setter
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private Set<PersonSkill> personSkills=new HashSet<>();
    
	@Override
	public String toString() {
		return "Person";
	}
	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}
}
