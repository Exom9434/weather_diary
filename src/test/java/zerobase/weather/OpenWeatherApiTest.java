package zerobase.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import zerobase.weather.dto.WeatherResponseDto;
import zerobase.weather.entity.Weather;
import zerobase.weather.repository.WeatherRepository;
import zerobase.weather.service.WeatherService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class OpenWeatherApiTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchWeatherFromApi() {
        // given
        WeatherResponseDto.Main main = new WeatherResponseDto.Main();
        main.setTemp(15.0);

        WeatherResponseDto.WeatherInfo info = new WeatherResponseDto.WeatherInfo();
        info.setMain("Clear");

        WeatherResponseDto response = new WeatherResponseDto();
        response.setMain(main);
        response.setWeather(List.of(info));

        when(restTemplate.getForObject(anyString(), WeatherResponseDto.class))
                .thenReturn(response);

        // when
        Weather weather = weatherService.fetchWeatherFromApi("Seoul");

        // then
        assertEquals("Clear", weather.getWeather());
        assertEquals(15.0, weather.getTemperature());
    }
}
