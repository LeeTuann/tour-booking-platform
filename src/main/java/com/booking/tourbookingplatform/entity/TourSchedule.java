package com.booking.tourbookingplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tour_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourSchedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id",nullable = false)
    private Tour tour;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private BigDecimal infantPrice;
    @Column(nullable = false)
    private Integer maxCapacity;
    private Integer bookedSeats;
    private Integer heldSeats;
    private String status;
    @Version
    private Integer version;

}
