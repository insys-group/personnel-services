package com.insys.trapps.model;

import lombok.*;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "OPPORTUNITY_STEP")
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityStep extends AbstractEntity{

	@Getter
	@Setter
	@NonNull
	@Column(name = "STEP_TIMESTAMP", nullable = false)
	private Timestamp stepTimestamp;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "OPPORTUNITY_ID")
	private Opportunity opportunity;

	public OpportunityStep(Opportunity opportunity, String comments , Timestamp stepTimestamp) {
		super();
		this.opportunity = opportunity;
		this.comments = comments;
		this.stepTimestamp = stepTimestamp;
	}
}
