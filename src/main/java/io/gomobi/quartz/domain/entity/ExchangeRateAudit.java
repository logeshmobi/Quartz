package io.gomobi.quartz.domain.entity;

import io.gomobi.quartz.common.constant.RequestSource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class ExchangeRateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime requestTimeStamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestSource requestSource;

    @Column(nullable = false, length = 50)
    private String requestBy;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal requestAmount;

    @Column(nullable = false, unique = true)
    private String callReference;

    @Column(nullable = false, length = 50)
    private String baseCurrency;

    @Column(nullable = false)
    private String targetCurrency;

}
