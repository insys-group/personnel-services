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
@Table(name = "OPPORTUNITY_STEP")
@EqualsAndHashCode(of = {"comments", "stepTimestamp"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class OpportunityStep implements Serializable {
	private static final long serialVersionUID = 2759280838440810254L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

//    @Version
//    @Getter
//    @Setter
//    @Column(name = "VERSION")
//    private Long version;

    @Getter
    @Setter
    @NonNull
    @Column(name = "STEP_TIMESTAMP" , nullable = false)
    private Timestamp stepTimestamp;

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "OPPORTUNITY_ID", nullable = false)
    private Opportunity opportunity;
	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}

}
