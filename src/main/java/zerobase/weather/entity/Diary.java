package main.java.zerobase.weather.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키

    @Column(nullable = false)
    private LocalDate date;  // 작성 날짜

    @Column(nullable = false)
    private String weather;  // 날씨 정보 (ex: Clear, Rainy)

    @Column(nullable = false)
    private double temperature;  // 기온

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;  // 일기 내용

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 생성일

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}