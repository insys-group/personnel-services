package com.insys.trapps.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "contract_detail")
@EqualsAndHashCode(exclude = {"contract"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDetail implements Serializable {
	private static final long serialVersionUID = -3721978464710098474L;

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
    @ManyToOne
    @NonNull
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Getter
    @Setter
    @NonNull
    @Column(name = "rate")
    private BigDecimal rate;

    @Getter
    @Setter
    @NonNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @Getter
    @Setter
    @NonNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "comments")
    @Getter
    @Setter
    @NonNull
    protected String comments;
	
//	@PrePersist
//	public void init() {
//		if(this.version==null) {
//			this.version=1L;
//		}
//	}

}
