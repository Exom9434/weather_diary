package zerobase.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zerobase.weather.entity.Weather;
import zerobase.weather.repository.WeatherRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    /**
     * OpenWeatherMap API를 호출하여 날씨 정보를 가져옴
     */
    public Weather fetchWeatherFromApi(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("q", city)
                .queryParam("appid", weatherApiKey)
                .queryParam("units", "metric")  // 섭씨 온도로 설정
                .build()
                .toString();

        var response = restTemplate.getForObject(url, WeatherResponseDto.class);
        if (response != null && response.getWeather() != null && !response.getWeather().isEmpty()) {
            return Weather.builder()
                    .date(LocalDate.now())
                    .weather(response.getWeather().get(0).getMain())
                    .temperature(response.getMain().getTemp())
                    .build();
        }
        throw new RuntimeException("날씨 정보를 가져올 수 없습니다.");
    }

    /**
     * 특정 날짜의 날씨 데이터를 조회 (DB에서 가져옴)
     */
    public Optional<Weather> getWeatherByDate(LocalDate date) {
        return weatherRepository.findByDate(date);
    }

    /**
     * 날씨 데이터를 DB에 저장
     */
    public void saveWeatherData(Weather weather) {
        weatherRepository.save(weather);
    }
}
