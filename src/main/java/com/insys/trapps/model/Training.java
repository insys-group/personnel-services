package com.insys.trapps.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
@AllArgsConstructor
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
	private long id;

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
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, targetEntity = TrainingTask.class)
	private Set<TrainingTask> tasks = new HashSet<>();
    

}