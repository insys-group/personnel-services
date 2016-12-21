package com.insys.trapps.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.*;

//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Version
    @NonNull
    @Getter
    @Setter
    @Column(name = "VERSION")
    private Long version;

    @Getter
    @Setter
    @NonNull
    private String name;
}
