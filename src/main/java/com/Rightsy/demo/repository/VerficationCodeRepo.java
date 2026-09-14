package com.Rightsy.demo.repository;

import com.Rightsy.demo.Dto.OtpType;
import com.Rightsy.demo.entity.VerficationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VerficationCodeRepo extends JpaRepository<VerficationCode,Long> {

    Optional<VerficationCode>findTopByEmailAndTypeAndUsedFalseOrderByCreatedAtDesc(
            String email,
            OtpType type
    );

    void deleteByExpiresAtBefore(LocalDateTime now);

}
