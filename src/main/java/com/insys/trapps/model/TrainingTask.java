package com.insys.trapps.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "TRAINING_TASK")
@EqualsAndHashCode(of = {"name"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingTask implements Serializable {
	
	private static final long serialVersionUID = 9124554833641859811L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
	
    @Getter
    @Setter
    @Column(name = "NAME", nullable = false)
    @NonNull
    private String name;
    
    @Getter
    @Setter
    @Column(name = "Weblink")
    @NonNull
    private String weblink;
    
    @Getter
    @Setter
    @Column(name = "COMPLETED")
    private boolean completed;
    
    @Getter
	@Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;
	
}
