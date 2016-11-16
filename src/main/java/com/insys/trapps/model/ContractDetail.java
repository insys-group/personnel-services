package com.insys.trapps.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by vnalitkin on 11/17/2016.
 */
@Entity
@Table(name = "CONTRACT_DETAIL")
@AllArgsConstructor
@NoArgsConstructor
public class ContractDetail extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "CONTRACT_ID")
    private Contract contract;

    @Getter
    @Setter
    @NonNull
    @Column(name = "RATE")
    private BigDecimal rate;

    @Getter
    @Setter
    @NonNull
    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Getter
    @Setter
    @NonNull
    @Column(name = "END_DATE")
    private LocalDate endDate;

}
