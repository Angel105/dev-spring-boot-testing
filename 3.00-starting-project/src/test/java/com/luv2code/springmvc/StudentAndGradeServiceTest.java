package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDAO;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDAO studentDAO;

    @BeforeEach
    public void setUpDatabase() {
        jdbcTemplate.execute("INSERT INTO student(id, firstname, lastname, email_address) " +
                "VALUES (1, 'John', 'Doe', 'john.doe@gmail.com')");
    }

    @Test
    public void createStudentTest() {
        studentService.createStudent("John", "Doe", "john.doe@luv2code_school.com");

        CollegeStudent student = studentDAO.findByEmailAddress("john.doe@luv2code_school.com");

        assertEquals("john.doe@luv2code_school.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void isStudentNullCheck() {

        assertTrue(studentService.checkIfStudentIsNull(1));

        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService() {
        Optional<CollegeStudent> deleteCollegeStudent = studentDAO.findById(1);

        assertTrue(deleteCollegeStudent.isPresent(), "Return True");

        studentService.deleteStudent(1);

        deleteCollegeStudent = studentDAO.findById(1);

        assertFalse(deleteCollegeStudent.isPresent(), "Return False");
    }

    @Sql("/insertData.sql")
    @Test
    public void getGradebookService() {
        Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent: iterableCollegeStudents) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(5, collegeStudents.size());
    }

    @AfterEach
    public void setUpAfterTransaction() {
        jdbcTemplate.execute("DELETE FROM student");
    }

}
