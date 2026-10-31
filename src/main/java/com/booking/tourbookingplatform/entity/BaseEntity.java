package com.booking.tourbookingplatform.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String tourType;
    private String departureLocation;
    private String durationDays;
    private String durationNights;
    private BigDecimal basePrice;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String inclusions;
    @Column(columnDefinition = "TEXT")
    private String exclusions;

}
