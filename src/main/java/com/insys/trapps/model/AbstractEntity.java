package com.insys.trapps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class AbstractEntity {
    @Id
//    @TableGenerator(
//            name="SEQUENCES",
//            table="SEQUENCES",
//            pkColumnName="GEN_KEY",
//            valueColumnName="GEN_VALUE",
//            pkColumnValue="SEQUENCE_ID",
//            allocationSize=1)
//    @GeneratedValue(strategy=TABLE, generator="SEQUENCES")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    /*
    @Getter
    @Setter
    @Version
    @Column(name = "VERSION")
    private Long version = 1L;
     */
    
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VERSION")
    private Date version;
}
