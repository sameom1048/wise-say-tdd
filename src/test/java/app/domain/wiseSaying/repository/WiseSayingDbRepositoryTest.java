package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Page;
import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class WiseSayingDbRepositoryTest {

    private static final WiseSayingDbRepository wiseSayingDbRepository = new WiseSayingDbRepository();


    @BeforeAll
    static void dropTable() {
        wiseSayingDbRepository.createWiseSayingTable();
    }

    @BeforeEach
    void truncateArticleTable() {
        wiseSayingDbRepository.truncateWiseSayingTable();
    }

    @Test
    @DisplayName("save test")
    void t1() {
        WiseSaying wiseSaying = new WiseSaying("현재를 사랑하라", "작자미상");
        wiseSaying = wiseSayingDbRepository.save(wiseSaying);

        Optional<WiseSaying> opWiseSaying = wiseSayingDbRepository.findById(wiseSaying.getId());

        WiseSaying found = opWiseSaying.orElse(null);

        assertThat(wiseSaying.getId()).isEqualTo(1);
        assertThat(found).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("명언 삭제")
    void t2() {

        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");

        wiseSayingDbRepository.save(wiseSaying);
        String filePath = WiseSayingFileRepository.getFilePath(wiseSaying.getId());

        boolean delRst = wiseSayingDbRepository.deleteById(1);

        boolean rst = Files.exists(Path.of(filePath));
        assertThat(rst).isFalse();
        assertThat(delRst).isTrue();
    }

    @Test
    @DisplayName("build 하면 모든 명언을 모아 하나의 파일로 저장")
    void t4() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa", "bbb");
        wiseSayingDbRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("ccc", "ddd");
        wiseSayingDbRepository.save(wiseSaying2);

        wiseSayingDbRepository.build();

        String jsonStr = Util.File.readAsString(WiseSayingFileRepository.getBuildPath());

        assertThat(jsonStr)
                .isEqualTo("""
                        [
                            {
                                "id" : 1,
                                "content" : "aaa",
                                "author" : "bbb"
                            },
                            {
                                "id" : 2,
                                "content" : "ccc",
                                "author" : "ddd"
                            }
                        ]
                        """.stripIndent().trim());

    }

    @Test
    @DisplayName("현재 저장된 명언의 개수를 가져오는 count")
    void t5() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa", "bbb");
        wiseSayingDbRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("ccc", "ddd");
        wiseSayingDbRepository.save(wiseSaying2);


        long count = wiseSayingDbRepository.count();

        assertThat(count)
                .isEqualTo(2);

    }

    @Test
    @DisplayName("페이지 정보와 결과 가져오기")
    void t6() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa", "bbb");
        wiseSayingDbRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("ccc", "ddd");
        wiseSayingDbRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("eee", "fff");
        wiseSayingDbRepository.save(wiseSaying3);

        int itemsPerPage = 5;
        int page = 1;
        Page<WiseSaying> pageContent = wiseSayingDbRepository.findAll(itemsPerPage, page);

        List<WiseSaying> wiseSayings = pageContent.getContent();
        int totalItems = pageContent.getTotalItems();
        int totalPages = pageContent.getTotalPages();

        assertThat(wiseSayings).hasSize(3);

        assertThat(totalItems)
                .isEqualTo(3);

        assertThat(totalPages)
                .isEqualTo(1);
    }
}
