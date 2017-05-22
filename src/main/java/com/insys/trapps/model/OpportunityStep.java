package com.insys.trapps.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Version;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "opportunity_step")
@EqualsAndHashCode(of = {"comments", "stepTimestamp"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpportunityStep implements Serializable {
	private static final long serialVersionUID = 2759280838440810254L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "step_timestamp" , nullable = false)
    private Timestamp stepTimestamp;

    @Column(name = "comments")
    @Getter
    @Setter
    @NonNull
    protected String comments;

}
