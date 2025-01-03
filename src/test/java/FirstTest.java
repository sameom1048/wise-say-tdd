import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {
    void t2() {
        // aaa가 출력되는가?
        // assertThat(result).isEqualTo("aaa");
    }
    @Test
    void t3() {
        String out = TestBot.run("종료");

        assertThat(out.toString()).contains("명언앱을 종료합니다.");
        // 출력값을 체크
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t4() {
        String out = TestBot.run("종료");

        assertThat(out.toString())
                .containsSubsequence("== 명언 앱 ==", "명언앱을 종료합니다.");
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력")
    void t5() {
        String out = TestBot.run("""
            등록
            현재를 사랑하라.
            작자미상
            """);

        assertThat(out.toString())
                .containsSubsequence("명언 : ", "작가 : ");
    }
}