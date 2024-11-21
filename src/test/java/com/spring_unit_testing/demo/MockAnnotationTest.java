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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = com.spring_unit_testing.demo.DemoApplication.class)
public class MockAnnotationTest {

  @Autowired
  private ApplicationContext context;

  @Autowired
  private CollegeStudent student;

  @Autowired
  private StudentGrades studentGrades;

  @MockBean
  private ApplicationDao applicationDao;

  @Autowired
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

  @Test
  @DisplayName("When and Verify v2")
  public void whenAndVerifyV2() {
    var mathGradeResults = studentGrades.getMathGradeResults();

    var applicationDaoMock = Mockito.mock(ApplicationDao.class);
    when(applicationDaoMock.addGradeResultsForSingleClass(mathGradeResults)).thenReturn(100.0);

    var service = new ApplicationService(applicationDaoMock);

    var studentMathGradeResults = student.getStudentGrades().getMathGradeResults();
    var result = service.addGradeResultsForSingleClass(studentMathGradeResults);
    assertEquals(100.0, result);

    verify(applicationDaoMock, times(1)).addGradeResultsForSingleClass(mathGradeResults);
  }

  @Test
  @DisplayName("Thrown an Exception")
  public void throwAnException() {
    var student = context.getBean("collegeStudent");

    when(applicationDao.checkNull(student))
        .thenThrow(new RuntimeException())
        .thenReturn("Does not throw exception on second time");

    assertThrows(RuntimeException.class, () -> {
      applicationService.checkNull(student);
    });

    assertEquals("Does not throw exception on second time", applicationService.checkNull(student));

    verify(applicationDao, times(2)).checkNull(student);
  }

  @Test
  public void readingPrivateFields() {
    var student = context.getBean("collegeStudent", CollegeStudent.class);
    student.setId(1);
    student.setFirstname("Juan Miguel");

    var firstName = (String) ReflectionTestUtils.getField(student, "firstname");

    var idAndFirstName = ReflectionTestUtils.<String>invokeMethod(student, "getIdAndFirstName", "+");

    assertEquals("Juan Miguel", firstName);
    assertEquals("1 + Juan Miguel", idAndFirstName);
  }
}
