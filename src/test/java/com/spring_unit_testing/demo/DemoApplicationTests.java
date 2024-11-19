package com.spring_unit_testing.demo;

import com.spring_unit_testing.demo.models.CollegeStudent;
import com.spring_unit_testing.demo.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	private static int count = 0;

	@Value("${info.app.name}")
	private String appName;

	@Value("${info.app.description}")
	private String appDescription;

	@Value("${info.app.version}")
	private String appVersion;

	@Autowired
	private CollegeStudent student;

	@Autowired
	private StudentGrades studentGrades;

	@BeforeEach
	public void beforeEach() {
		count = count + 1;
		System.out.println("Testing: " + appName + " which is " + appDescription + " Version " + appVersion + ". Execution of the testMethod " + count) ;

		student.setFirstname("Juan Miguel");
		student.setLastname("Paulino");
		student.setEmailAddress("juanmiguel431@gmail.com");

		studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
		student.setStudentGrades(studentGrades);
	}

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Add grade results for student grades")
	public void addGradeResultsForStudentGrades() {
		var mathGradeResults = student.getStudentGrades().getMathGradeResults();
		assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(mathGradeResults));
	}

	@Test
	@DisplayName("Add grade results for student grades not equal")
	public void addGradeResultsForStudentGradesAssertNotEquals() {
		assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
				student.getStudentGrades().getMathGradeResults()
		));
	}

	@Test
	@DisplayName("Is Grade greater true")
	public void isGradeGreaterStudentGradesAssertTrue() {
		assertTrue(studentGrades.isGradeGreater(90, 75), "Failure - should be true");
	}

	@Test
	@DisplayName("Is grade greater false")
	public void isGradeGreaterStudentGradesAssertFalse() {
		assertFalse(studentGrades.isGradeGreater(89, 92), "Failure - should be false");
	}

	@Test
	@DisplayName("Check Null for student grades")
	public void checkNullForStudentGrades() {
		assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()), "Object should not be null");
	}
}
