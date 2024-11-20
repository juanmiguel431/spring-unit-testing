package com.spring_unit_testing.demo;

import com.spring_unit_testing.demo.dao.ApplicationDao;
import com.spring_unit_testing.demo.models.CollegeStudent;
import com.spring_unit_testing.demo.models.StudentGrades;
import com.spring_unit_testing.demo.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    studentGrades.setMathGradeResults(List.of(95.0, 87.6, 100.0));
    student.setStudentGrades(studentGrades);
  }

  @Test
  @DisplayName("When and Verify")
  public void whenAndVerify() {
    var mathGradeResults = studentGrades.getMathGradeResults();
    when(applicationDao.addGradeResultsForSingleClass(mathGradeResults)).thenReturn(100.0);

    var studentMathGradeResults = student.getStudentGrades().getMathGradeResults();
    var result = applicationService.addGradeResultsForSingleClass(studentMathGradeResults);
    assertEquals(100.0, result);

    verify(applicationDao, times(1)).addGradeResultsForSingleClass(mathGradeResults);
  }
}
