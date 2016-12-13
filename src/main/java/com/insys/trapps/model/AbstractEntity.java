package com.insys.trapps.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @TableGenerator(
            name="SEQUENCES",
            table="SEQUENCES",
            pkColumnName="GEN_KEY",
            valueColumnName="GEN_VALUE",
            pkColumnValue="SEQUENCE_ID",
            allocationSize=1)
    @GeneratedValue(strategy=TABLE, generator="SEQUENCES")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Version
    @NonNull
    @Column(name = "VERSION")
    private Long version = 1L;

}
