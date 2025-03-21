package zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.weather.entity.Weather;
import java.time.LocalDate;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    // 특정 날짜의 날씨 조회
    Optional<Weather> findByDate(LocalDate date);
}
