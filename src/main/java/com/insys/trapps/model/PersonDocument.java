package com.insys.trapps.model;

import java.util.Arrays;
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
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERSON_DOCUMENT")
@EqualsAndHashCode(of = {"fileName"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class PersonDocument {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Version
    @Getter
    @Setter
    @Column(name = "VERSION")
    private Long version;

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
	@Override
	public String toString() {
		return "PersonDocument [person=" + person + ", fileName=" + fileName + ", uploadTimestamp=" + uploadTimestamp
				+ ", document=" + Arrays.toString(document) + ", getId()=" + getId() + ", getVersion()=" + getVersion()
				+ "]";
	}
}
