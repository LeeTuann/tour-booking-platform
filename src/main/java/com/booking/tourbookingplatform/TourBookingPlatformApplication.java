package com.booking.tourbookingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TourBookingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourBookingPlatformApplication.class, args);
    }

}
