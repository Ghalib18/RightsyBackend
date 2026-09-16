package com.Rightsy.demo.entity;

import com.Rightsy.demo.Dto.OtpType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerficationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false,length = 6)
    private String code;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OtpType type;
    private Boolean used=false;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private Boolean verified;
}
