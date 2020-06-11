package org.hjjang.junitexample;

//import org.junit.jupiter.api.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @DisplayName("스터디 만들기 반복 \uD83D\uDE31")
    @RepeatedTest(value = 10,name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void create_study_repeatTest(RepetitionInfo repetitionInfo){
        System.out.println("RepeatTest : "+repetitionInfo.getCurrentRepetition() + "/"+
                repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("파라미터 메시지")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고","았네요."})
    void parameterizedTest(String message){
        System.out.println(message);
    }
    @Test
    @DisplayName("스터디 만들기 @madeByFast \uD83D\uDE31")
    @FastTest
    void create_new_study_madeTagByFast(){
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println("create_new_study_madeTagByFast");
    }


    @Test
    @DisplayName("스터디 만들기 Fast \uD83D\uDE31")
    @Tag("fast")
    void create_new_study_fast(){
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println("create_new_study_fast");
    }

    @Test
    @DisplayName("스터디 만들기 Slow \uD83D\uDE31")
    @Tag("slow")
    void create_new_study_slow(){
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println("create_new_study_slow");
    }


    @Test
    @DisplayName("크레이트1")
    void create1(){
        Study study = new Study(10);
//        assertNotNull(study);
//        // 1. 기대하는값 2. 실제값
//        assertEquals(StudyStatus.DRAFT,study.getStatus(),()->"스터디를 처음만들면 상태값이 DRAFT여야 한다.");
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
//            @Override
//            public String get() {
//                return "스터디를 처음 만들면 DRAFT 상태다.";
//            }
//        });
//
//        assertTrue(1 < 2);
//
//        assertTrue(study.getLimit() > 0, "스터디 최대 참석가능 인원은 0 보다 커야한다.");

        assertAll(
                ()->assertNotNull(study),
                ()->assertEquals(StudyStatus.DRAFT,study.getStatus(),()->"스터디를 처음만들면 상태값이 DRAFT여야 한다."),
                ()->assertTrue(study.getLimit() > 0, "스터디 최대 참석가능 인원은 0 보다 커야한다.")
        );
        System.out.println("create1");
    }

    @Test
    @DisplayName("OS환경 \uD83D\uDE31")
    @EnabledOnOs(OS.WINDOWS)
    void os_test_byWin(){
        System.out.println("Windows");
    }
    @Test
    @DisplayName("OS환경 \uD83D\uDE31")
    @EnabledOnOs(OS.LINUX)
    void os_test_byLinux(){
        System.out.println("linux");
    }

    @Test
    @DisplayName("OS환경_윈도우 테스트안함 \uD83D\uDE31")
    @DisabledOnOs(OS.WINDOWS)
    void os_test_byWin_Disabled(){
        System.out.println("Disabled Window Test");
    }

    @Test
    @DisplayName("OS환경_멀티 \uD83D\uDE31")
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    void os_test_byMulti(){
        System.out.println("multi");
    }

    @Test
    @DisplayName("환경변수 \uD83D\uDE31")
    void env_test(){
        System.out.println("env_test");
        String test_env = System.getenv("TEST_ENV");

        System.out.println(test_env);
//        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        assumingThat("LOCAL".equalsIgnoreCase(test_env),() -> {
            System.out.println("Local");
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        assumingThat("hjjang".equalsIgnoreCase(test_env),() -> {
            System.out.println("hJJANG");
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

    }

    @Test
    @Disabled
    void craete2(){
        System.out.println("craete2");
    }

    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    void create1_new_study(){
//        Study study = new Study(-10);
//        assertNotNull(study);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));

        String message = exception.getMessage();

        assertEquals("limit 는 0보다 커야한다.",message);
        System.out.println("create1_new_study");
    }

    @Test
    @DisplayName("스터디 만들기2 \uD83D\uDE31")
    void create1_new_study2(){
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        System.out.println("create1_new_study");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("afterAll");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("beforeEach");
    }
    @AfterEach
    void afterEach(){
        System.out.println("afterEach");
    }
}