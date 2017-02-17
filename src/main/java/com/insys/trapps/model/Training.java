package com.insys.trapps.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "TRAINING")
@EqualsAndHashCode(of = { "name"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@SuppressFBWarnings(value = { "EI_EXPOSE_REP", "EI_EXPOSE_REP2" })
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", nullable = true, insertable = true, updatable = true)
	private Address location;

	@Getter
	@Setter
	private boolean online = true;

	@Getter
	@Setter
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "TRAINING_TASKS"
			, joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "ID")
			, inverseJoinColumns = @JoinColumn(name = "tasks_id", referencedColumnName = "ID")
	)
	private Set<TrainingTask> tasks = new HashSet<>();

	@Getter
	@Setter
	@OneToMany(mappedBy = "training", cascade = CascadeType.MERGE)
	private Set<PersonTraining> personTrainings = new HashSet<>();


}