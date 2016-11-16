package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter
    @Setter
    protected Long id;

    @Column(name = "CREATE_DATE")
    @Getter
    @Setter
    protected Timestamp createDate;

    @Column(name = "VERSION")
    @Getter
    @Setter
    @Version
    protected Long version;

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

}
