package com.booking.tourbookingplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour extends BaseEntity{
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
