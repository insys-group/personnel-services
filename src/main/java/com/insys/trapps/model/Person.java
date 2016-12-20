package com.insys.trapps.model;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(of = {"email"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Person extends AbstractEntity {
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
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID", nullable = false)
    private Business business;

    @Getter
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<PersonDocument> documents;
    
    @Getter
    @Setter
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<PersonSkill> skills;

}
