package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRAINING")
@EqualsAndHashCode(of = { "name", "location", "online", "startDate" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
public class Training implements Serializable {

	private static final long serialVersionUID = -3259421945445755768L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@Column(name = "NAME", nullable = false)
	private String name;

	@Getter
	@Setter
	@Column(name = "TRAINEES")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "person_training", 
		joinColumns = @JoinColumn(name = "training_id"), 
		inverseJoinColumns = @JoinColumn(name = "person_id"))
	private Set<Person> trainees;

	@Getter
	@Setter
    @Column(name = "PROGRESS_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProgressType progress;
	
	@Getter
	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", nullable = true, insertable = true, updatable = true)
	private Address location;

	@Getter
	@Setter
	@Column(name = "ONLINE")
	private boolean online = true;

	@Getter
	@Setter
	@Temporal(value = TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	@Getter
	@Setter
	@Temporal(value = TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;

	@Autowired
	@Getter
	@Setter
	@OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TrainingTask> tasks;

}