package io.gomobi.quartz.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Entity
@Table
public class ExchangeRateProviderConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column( unique = true, nullable = false, length = 30)
    private String providerName;

    @Column( unique = true, nullable = false, length = 75)
    private String providerUrl;

    @Column(unique = true, nullable = false, length = 75)
    private String clientId;

    @Column(unique = true, nullable = false, length = 75)
    private String clientSecret;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private Status status;

    @Column(nullable = false, length = 30)
    private String subscriptionType;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    @Column(nullable = false)
    private BigDecimal fee;

    @Column(nullable = false)
    private int maxRequestLimit;

    @Column(nullable = false)
    private int remainingRequestCount;

    @Column(nullable = false)
    private TimeUnit scheduleUnit;

    @Column(nullable = false)
    private short scheduleInterval;

    public enum Status{
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        TIME_EXPIRED,
        REQUEST_LIMIT_EXPIRED,
        TERMINATED
    }
}