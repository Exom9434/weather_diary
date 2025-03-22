package main.java.zerobase.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequestDto {

    @NotBlank(message = "일기 내용은 비어 있을 수 없습니다.")
    private String text;
}
