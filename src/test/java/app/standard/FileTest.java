package app.standard;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    // 3. 테스트 시작 전에 test 폴더 생성
    // 테스트 전처리

    @BeforeAll
    static void beforeAll() {
        System.out.println("테스트 실행 전에 한번 실행");
        Util.File.createDir("test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("테스트 실행 후에 한번 실행");
        Util.File.delete("test");
    }

    // 4. 테스트 종료 후에 test 폴더 삭제
    // 테스트 후처리

    @Test
    @DisplayName("최초의 파일 테스트")
    void t1() {
        Util.File.test();
    }

    @Test
    @DisplayName("파일 생성. 내용이 없는 빈 파일 생성")
    void t2() {

        String file = "test/test.txt";

        Util.File.createFile(file); // 파일 생성 ok

        assertThat(Files.exists(Paths.get(file)))
                .isTrue();
    }

    @Test
    @DisplayName("파일 내용 읽어오기")
    void t3() {

        // 파일을 Hello, World 내용으로 생성
        String file = "test/test.txt";
        String testContent = "Hello, World";

        Util.File.write(file, testContent);
        String content = Util.File.readAsString(file);

        assertThat(content)
                .isEqualTo(testContent);
    }

    @Test
    @DisplayName("파일 내용 수정")
    void t4() {

        String file = "test/test.txt";
        String writeContent = "modify content";

        Util.File.write(file, writeContent);
        String readContent = Util.File.readAsString(file);

        assertThat(readContent)
                .isEqualTo(writeContent);
    }

    @Test
    @DisplayName("파일 삭제")
    void t5() {

        String file = "test/test.txt";

        // test3.txt 파일 생성
        Util.File.createFile(file);
        assertThat(Files.exists(Paths.get(file)))
                .isTrue();

        // test3.txt 파일 삭제
        Util.File.delete(file);

        // test3.txt 존재 여부 확인
        assertThat(Files.exists(Paths.get(file)))
                .isFalse();

    }

    @Test
    @DisplayName("폴더 생성")
    void t6() {

        String dirPath = "test";

        Util.File.createDir(dirPath);

        assertThat(Files.exists(Paths.get(dirPath)))
                .isTrue();

        assertThat(Files.isDirectory(Path.of(dirPath)))
                .isTrue();

    }

    @Test
    @DisplayName("폴더 삭제")
    void t7() {

        String dirPath = "test";

        Util.File.delete(dirPath);

        assertThat(Files.exists(Paths.get(dirPath)))
                .isFalse();
    }

}