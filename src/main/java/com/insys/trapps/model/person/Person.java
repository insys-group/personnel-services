package com.insys.trapps.model.person;

import com.fasterxml.jackson.annotation.*;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.PersonTraining;
import com.insys.trapps.model.interview.Interview;
import com.insys.trapps.model.security.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;

    @Getter
    @Setter
    @Column(name = "skype")
    private String skype;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "title", nullable = true)
    private String title;

    @Getter
    @Setter
    @Column(name = "person_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, targetEntity = PersonDocument.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonDocument> personDocuments = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, targetEntity = PersonSkill.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonSkill> personSkills = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, targetEntity = PersonTraining.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonTraining> personTrainings = new HashSet<>();

    public void addDocument(PersonDocument personDocument){
        personDocuments.add(personDocument);
    }

}
