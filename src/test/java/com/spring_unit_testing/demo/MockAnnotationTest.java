package com.spring_unit_testing.demo;

import com.spring_unit_testing.demo.dao.ApplicationDao;
import com.spring_unit_testing.demo.models.CollegeStudent;
import com.spring_unit_testing.demo.models.StudentGrades;
import com.spring_unit_testing.demo.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = com.spring_unit_testing.demo.DemoApplication.class)
public class MockAnnotationTest {

  @Autowired
  private ApplicationContext context;

  @Autowired
  private CollegeStudent student;

  @Autowired
  private StudentGrades studentGrades;

  @Mock
  private ApplicationDao applicationDao;

  @InjectMocks
  private ApplicationService applicationService;

  @BeforeEach
  public void beforeEach() {
    student.setFirstname("Juan Miguel");
    student.setLastname("Paulino Carpio");
    student.setEmailAddress("juanmiguel431@gmail.com");

    student.setStudentGrades(studentGrades);
  }
}
