package com.insys.trapps.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OPPORTUNITY_CONTACT")
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
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
    @Column(name = "IS_COMPLETED")
    private boolean isCompleted;
    
    @Getter
	@Setter
    @ManyToOne
    @JoinColumn(name = "TRAINING_ID")
    private Training training;
	
}
