package main.java.zerobase.weather.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키

    @Column(nullable = false)
    private LocalDate date;  // 날짜

    @Column(nullable = false)
    private String weather;  // 날씨 정보 (ex: Clear, Rainy)

    @Column(nullable = false)
    private double temperature;  // 기온
}
