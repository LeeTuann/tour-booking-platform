package com.booking.tourbookingplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String bookingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_schedule_id",nullable = false)
    private TourSchedule tourSchedule;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private Integer totalAdults;
    private Integer totalChildren;
    private Integer totalInfants;
    private BigDecimal totalAmount;
    private String status;
    private Instant paymentDeadline;
}
