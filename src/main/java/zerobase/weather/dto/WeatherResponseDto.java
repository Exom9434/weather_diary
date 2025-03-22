package main.java.zerobase.weather.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponseDto {

    private List<WeatherInfo> weather;
    private Main main;

    @Getter
    @Setter
    public static class WeatherInfo {
        private String main;        // ex: "Clear"
        private String description; // ex: "clear sky"
    }

    @Getter
    @Setter
    public static class Main {
        private double temp; // ex: 15.5
    }
}
