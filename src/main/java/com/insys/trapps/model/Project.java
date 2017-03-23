package com.insys.trapps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.insys.trapps.model.person.Person;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Serializable {
	private static final long serialVersionUID = -5560389393119101131L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    protected String name;

    @Getter
    @Setter
    protected String requirements;

    @Getter
    @Setter
    protected String comments;

    @Getter
    @Setter
    @OneToMany
    protected List<Person> persons;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;

}