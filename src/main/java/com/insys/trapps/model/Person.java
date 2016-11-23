package com.insys.trapps.model;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@EqualsAndHashCode(of = {"email"})
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    protected Long id;

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
    @NonNull
    @Column(name = "PERSON_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Getter
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PersonDocument> documents;
    
    //TODO - Enable when needed.
    /*
    @ManyToOne
    @JoinColumn(name = "person")
    private Business business;
    */
}