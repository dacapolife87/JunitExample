package org.hjjang.junitexample;

//import org.junit.jupiter.api.*;

import org.junit.jupiter.api.*;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

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
        assertTimeout(Duration.ofSeconds(100), () -> {
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