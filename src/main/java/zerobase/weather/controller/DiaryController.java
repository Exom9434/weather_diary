package main.java.zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.entity.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
@Tag(name = "Diary API", description = "일기 CRUD API") // Swagger 태그 추가
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 새로운 일기 생성 API
     */
    @PostMapping("/create")
    @Operation(summary = "새로운 일기 생성", description = "날씨 데이터와 함께 새로운 일기를 저장합니다.") // Swagger 설명 추가
    public ResponseEntity<String> createDiary(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam String text) {
        try {
            diaryService.createDiary(date, text);
            return ResponseEntity.ok("일기가 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일기 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 특정 날짜의 일기 조회 API
     */
    @GetMapping("/read")
    @Operation(summary = "특정 날짜의 일기 조회", description = "특정 날짜의 일기를 반환합니다.")
    public ResponseEntity<?> getDiary(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            List<Diary> diaries = diaryService.getDiaryByDate(date);
            if (diaries.isEmpty()) {
                return ResponseEntity.ok("해당 날짜에 저장된 일기가 없습니다.");
            }
            return ResponseEntity.ok(diaries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일기 조회 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 특정 기간의 일기 조회 API
     */
    @GetMapping("/read/all")
    @Operation(summary = "특정 기간의 일기 조회", description = "startDate와 endDate를 기준으로 일기 목록을 조회합니다.")
    public ResponseEntity<?> getDiaries(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            List<Diary> diaries = diaryService.getDiariesByPeriod(startDate, endDate);
            if (diaries.isEmpty()) {
                return ResponseEntity.ok("해당 기간에 저장된 일기가 없습니다.");
            }
            return ResponseEntity.ok(diaries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일기 조회 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 특정 날짜의 첫 번째 일기 수정 API
     */
    @PutMapping("/update")
    @Operation(summary = "특정 날짜의 일기 수정", description = "해당 날짜의 첫 번째 일기를 새로운 내용으로 수정합니다.")
    public ResponseEntity<String> updateDiary(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam String newText) {
        try {
            diaryService.updateDiary(date, newText);
            return ResponseEntity.ok("일기가 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일기 수정 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 특정 날짜의 모든 일기 삭제 API
     */
    @DeleteMapping("/delete")
    @Operation(summary = "특정 날짜의 일기 삭제", description = "해당 날짜에 저장된 모든 일기를 삭제합니다.")
    public ResponseEntity<String> deleteDiary(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            diaryService.deleteDiaries(date);
            return ResponseEntity.ok("일기가 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일기 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}
