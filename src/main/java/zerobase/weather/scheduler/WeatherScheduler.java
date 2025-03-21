package zerobase.weather.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zerobase.weather.entity.Weather;
import zerobase.weather.service.WeatherService;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WeatherService weatherService;

    /**
     * 매일 새벽 1시에 날씨 데이터를 API에서 가져와 저장
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void fetchAndSaveWeatherDaily() {
        System.out.println("🌤 [Scheduler] 날씨 데이터 가져오는 중...");
        
        // 1. OpenWeatherMap API에서 날씨 정보 가져오기
        Weather weather = weatherService.fetchWeatherFromApi("Seoul");

        // 2. DB에 저장
        weatherService.saveWeatherData(weather);

        System.out.println("✅ [Scheduler] 날씨 데이터 저장 완료: " + weather);
    }
}
