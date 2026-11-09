package com.booking.tourbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booking.tourbookingplatform.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrPhone(String email, String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
