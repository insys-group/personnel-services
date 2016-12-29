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
@EqualsAndHashCode(exclude = {"contract"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Version
    @NonNull
    @Getter
    @Setter
    @Column(name = "VERSION")
    private Long version;

    @Getter
    @Setter
    @ManyToOne
    @NonNull
    @JoinColumn(name = "CONTRACT_ID", nullable = false)
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

    @Column(name = "COMMENTS")
    @Getter
    @Setter
    @NonNull
    protected String comments;
}
