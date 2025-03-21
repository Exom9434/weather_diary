package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class WeatherApplicationTests {

    @Test
    void sampleTest() {
        assertThat("값이 존재함", 1, anything());
    }

    @Test
    void equalTest() {
        assertThat("1은 1과 같아야 함", 1, equalTo(1));
    }

    @Test
    void failTest() {
        // 이건 일부러 실패하는 테스트라면 OK, 아니면 수정하거나 주석처리 해도 돼
        // assertThat("이건 실패할 테스트입니다", 1, equalTo(2));
    }
}
