package main.java.zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.weather.entity.Diary;
import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    // 특정 날짜의 일기 목록 조회
    List<Diary> findAllByDate(LocalDate date);

    // 특정 날짜 범위 내의 일기 목록 조회
    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    // 특정 날짜의 첫 번째 일기 조회
    Diary findFirstByDate(LocalDate date);

    // 특정 날짜의 모든 일기 삭제
    void deleteAllByDate(LocalDate date);
}
