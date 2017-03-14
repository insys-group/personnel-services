package com.insys.trapps.model.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.insys.trapps.model.person.Person;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PERSON_DOCUMENT")
@EqualsAndHashCode(of = {"fileName"}, callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@ToString
public class PersonDocument implements Serializable {
	private static final long serialVersionUID = 4569824666811236042L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    @JsonBackReference
    private Person person;

    @Getter
    @Setter
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Getter
    @Setter
    @Column(name = "FILE_SIZE", nullable = false)
    private Long fileSize;
    
    @Getter
    @Setter
    @Column(name = "UPLOAD_TIMESTAMP", nullable = false)
    private Date uploadTimestamp;
    
    @Setter
    @SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
    @Lob
    @Column(name = "DOCUMENT", nullable = false)
    private byte[] document;

    @JsonIgnore
    public byte[] getDocument() {
    	return this.document;
    }

}
