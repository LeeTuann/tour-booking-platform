package com.booking.tourbookingplatform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_itineraries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourItinerary extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id",nullable = false)
    private Tour tour;
    private Integer dayNumber;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
}
