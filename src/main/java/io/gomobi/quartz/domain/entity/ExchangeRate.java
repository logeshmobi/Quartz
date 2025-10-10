package io.gomobi.quartz.domain.entity;


import io.gomobi.quartz.common.constant.CurrencyCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@ToString
@Table
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false, length = 25)
    private String providerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyCode baseCurrency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyCode targetCurrency;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal liveRate;

    @Column(nullable = false)
    private OffsetDateTime liveRateTimestamp;

    @PrePersist
    @PreUpdate
    private void validateCurrencies() {
        if (baseCurrency != null && baseCurrency.equals(targetCurrency)) {
            throw new IllegalArgumentException("Base currency and target currency cannot be the same.");
        }
    }
}

/*
* {
	"terms": "https://www.xe.com/legal/",
	"privacy": "http://www.xe.com/privacy.php",
	"from": "USD",
	"amount": 1.0,
	"timestamp": "2025-09-16T07:00:00Z",
	"to": [{
		"quotecurrency": "INR",
		"mid": 88.0250385858
	}]
}
* */