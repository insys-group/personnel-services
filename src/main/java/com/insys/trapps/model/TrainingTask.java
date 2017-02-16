package com.insys.trapps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TRAINING_TASK")
@EqualsAndHashCode(of = {"name","weblink","description"})
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@JsonRootName("task")
public class TrainingTask implements Serializable {
	
	private static final long serialVersionUID = 9124554833641859811L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private long id;
	
    @Getter
    @Setter
    @Column(nullable = false)
    @NonNull
    private String name;
	
    @Getter
    @Setter
    private String description;
    
    @Getter
    @Setter
    private String weblink;
    
    @Getter
    @Setter
    @Column(nullable = true)
    private boolean completed;
	
}
