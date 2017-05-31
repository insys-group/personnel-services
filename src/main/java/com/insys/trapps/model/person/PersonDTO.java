package com.insys.trapps.model.person;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.PersonTraining;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private PersonType personType;

    @Getter
    @Setter
    private Address address;

    @Getter
    @Setter
    private Business business;

}
