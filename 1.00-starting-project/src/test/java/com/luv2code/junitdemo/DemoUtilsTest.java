package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before the execution of each test method");
    }

    @DisplayName("Multiply")
    @Test
    void testMultiply() {

        assertEquals(12, demoUtils.multiply(4,3), "4*3 must be 12");

    }

    @Test
    @DisplayName("Equals and Not Equals")
    @Order(1)
    void testEqualsAndNotEquals() {
//    void test_Equals_And_Not_Equals() {

        System.out.println("Running test: testEqualsAndNotEquals");

        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1, 9), "1+9 must not be 6");
    }

    @Test
    @DisplayName("Null and Not Null")
    @Order(0)
    void testNullAndNotNull() {
//    void test_Null_And_Not_Null() {

        System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "luv2code";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @DisplayName("Same and Not Same")
    @Test
    void testSameAndNotSame() {

        String str = "luv2code";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to same object");
        assertNotSame(str, demoUtils.getAcademyDuplicate(), "Objects should not refer to same object");
    }

    @DisplayName("True and False")
    @Test
    @Order(30)
    void testTrueFalse() {
        int gradeOne = 10;
        int gradeTwo = 5;

        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should return true");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should return false");
    }

    @DisplayName("Array Equals")
    @Test
    void testArrayEquals() {

        String[] stringArray= {"A", "B", "C"};

        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
    }

    @DisplayName("Iterable equals")
    @Test
    void testIterableEquals() {

        List<String> theList = List.of("luv", "2", "code");

        assertIterableEquals(theList, demoUtils.getAcademyInList(), "Expected list should be same as actual list");
    }

    @DisplayName("Lines match")
    @Test
    @Order(50)
    void testLinesMatch() {
        List<String> theList = List.of("luv", "2", "code");

        assertLinesMatch(theList, demoUtils.getAcademyInList(), "Lines should match");
    }

    @DisplayName("Throws and Does Not Throw")
    @Test
    void testThrowsAndDoesNotThrow() {

        assertThrows(Exception.class, () -> { demoUtils.throwException(-1); }, "Should throw exception");
        assertDoesNotThrow(() -> { demoUtils.throwException(5); }, "Should not throw exception");
    }

    @DisplayName("Timeout")
    @Test
    void testTimeout() {

        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> { demoUtils.checkTimeout(); }, "Method should execute in 3 seconds");
    }


    /*
    @BeforeAll
    static void setupBeforeAll() {
        // by default, @BeforeAll method must be static
        System.out.println("@BeforeAll executes only once before all test methods execution in the class");
    }

    @AfterAll
    static void tearDownAfterAll() {
        // by default, @AfterAll method must be static
        System.out.println("@AfterAll executes only once after all test methods execution in the class");
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Running @AfterEach\n");
    }
    */

}
