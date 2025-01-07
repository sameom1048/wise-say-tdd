package app.standard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {

    @Test
    @DisplayName("Map을 Json으로 변환1 - 속성이 1개")
    void t1() {

        Map<String, Object> map = Map.of("name", "홍길동");

        String jsonStr = Util.Json.mapToJson(map);

        assertThat(jsonStr)
                .isEqualTo("""
                        {
                            "name" : "홍길동"
                        }
                        """.stripIndent().trim());

    }
}
