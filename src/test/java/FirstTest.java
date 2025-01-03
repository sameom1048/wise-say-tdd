import org.example.TestApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {
    void t2() {
        // aaa가 출력되는가?
        // assertThat(result).isEqualTo("aaa");
    }
    @Test
    void t3() {
        // 테스트봇 선입력
        Scanner sc = new Scanner("종료\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestApp app = new TestApp();
        app.run();
        assertThat(out.toString()).contains("명언앱을 종료합니다.");
        // 출력값을 체크
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t4() {
        Scanner sc = new Scanner("종료\n");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        TestApp app = new TestApp();
        app.run();

        assertThat(out.toString())
                .contains("== 명언 앱 ==")
                .contains("명언앱을 종료합니다.");
    }
}