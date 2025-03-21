package zerobase.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.entity.Diary;
import zerobase.weather.entity.Weather;
import zerobase.weather.repository.DiaryRepository;
import zerobase.weather.repository.WeatherRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final WeatherService weatherService;

    /**
     * 새로운 일기 생성 (날씨 데이터와 함께 저장)
     */
    @Transactional
    public void createDiary(LocalDate date, String text) {
        // 1. 날씨 정보 가져오기 (DB에 없으면 API 호출)
        Weather weather = weatherService.getWeatherByDate(date)
                .orElseGet(() -> weatherService.fetchWeatherFromApi("Seoul"));

        // 2. 일기 저장
        Diary diary = Diary.builder()
                .date(date)
                .weather(weather.getWeather())
                .temperature(weather.getTemperature())
                .text(text)
                .build();
        diaryRepository.save(diary);
    }

    /**
     * 특정 날짜의 일기 조회
     */
    @Transactional(readOnly = true)
    public List<Diary> getDiaryByDate(LocalDate date) {
        return diaryRepository.findAllByDate(date);
    }

    /**
     * 특정 기간의 일기 조회
     */
    @Transactional(readOnly = true)
    public List<Diary> getDiariesByPeriod(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    /**
     * 특정 날짜의 첫 번째 일기 수정
     */
    @Transactional
    public void updateDiary(LocalDate date, String newText) {
        Diary diary = diaryRepository.findFirstByDate(date);
        if (diary != null) {
            diary.setText(newText);
            diaryRepository.save(diary);
        } else {
            throw new RuntimeException("수정할 일기가 없습니다.");
        }
    }

    /**
     * 특정 날짜의 모든 일기 삭제
     */
    @Transactional
    public void deleteDiaries(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }
}
