package com.insys.trapps.model.person;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insys.trapps.model.Address;
import com.insys.trapps.model.Business;
import com.insys.trapps.model.PersonTraining;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person_document")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDocument implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

	@Getter
	@Setter
	private String fileName;

	@Getter
	@Setter
	private long fileSize;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String s3key;

    @Getter
    @Setter
    @Transient
    private byte[] file;

}
