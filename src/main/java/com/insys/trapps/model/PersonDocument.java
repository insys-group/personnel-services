package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.sql.SQLData;

@Entity
@Table(name = "PERSON_DOCUMENT")
@EqualsAndHashCode(exclude = {"person"}, callSuper = false)
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
    @Column(name = "DOCUMENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Getter
    @Setter
    @Lob
    @Column(name = "DOCUMENT", nullable = false)
    private byte[] document;
}
