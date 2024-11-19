package com.spring_unit_testing.demo;

import com.spring_unit_testing.demo.models.CollegeStudent;
import com.spring_unit_testing.demo.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

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
	}

	@Test
	void contextLoads() {
	}

}
