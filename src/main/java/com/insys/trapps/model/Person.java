package com.insys.trapps.model;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(exclude = {"email"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person extends AbstractEntity {
    @Getter
    @Setter
    @NonNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Getter
    @Setter
    @NonNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "PHONE")
    private String phone;

    @Getter
    @Setter
    @NonNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "TITLE", nullable = true)
    private String title;

    @Getter
    @Setter
    @NonNull
    @Column(name = "PERSON_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID", nullable = true)
    private Business business;

    @Getter
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PersonDocument> documents;
}
