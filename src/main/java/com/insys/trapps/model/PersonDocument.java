package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.sql.SQLData;

@Entity
@Table(name = "PERSON_DOCUMENT")
@EqualsAndHashCode(exclude = {"person"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
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
