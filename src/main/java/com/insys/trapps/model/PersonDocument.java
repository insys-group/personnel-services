package com.insys.trapps.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.*;

import javax.persistence.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.sql.SQLData;
import java.util.Date;

@Entity
@Table(name = "PERSON_DOCUMENT")
@EqualsAndHashCode(of = {"fileName"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class PersonDocument extends AbstractEntity {
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @Getter
    @Setter
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Getter
    @Setter
    @Column(name = "UPLOAD_TIMESTAMP", nullable = false)
    private Date uploadTimestamp;
    
    @Getter
    @Setter
    @SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
    @Lob
    @Column(name = "DOCUMENT", nullable = false)
    private byte[] document;
}
