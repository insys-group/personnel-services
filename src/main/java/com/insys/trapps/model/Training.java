package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRAINING")
@EqualsAndHashCode(of = {"name", "location", "isOnline", "startDate"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Training implements Serializable {
	
	private static final long serialVersionUID = -3259421945445755768L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
	
    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;
    

	@Getter
	@Setter
	@Column(name = "TRAINEES", nullable = false)
	@OneToMany(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Person> trainees = new HashSet<>();
    
    @Getter
    @Setter
    @Column(name = "PROGRESS", nullable = false)
    private ProgressType progress;
    
    @Getter
    @Setter
    @Column(name = "LOCATION")
    private Address location;
    
    @Getter
    @Setter
    @Column(name = "IS_ONLINE")
    private boolean isOnline;
    
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
    
    @Getter
    @Setter
    @OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "TRAINING_TASK"
            , joinColumns = @JoinColumn(name = "TRAINING_ID", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "TRAINING_TASK_ID", referencedColumnName = "ID")
    )
    private Set<TrainingTask> tasks;
    
    
    
}
