package com.cg.testStudent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.dao.IStudentDao;
import com.cg.dto.StudentDTO;
import com.cg.entities.Student;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.IStudentService;
import com.cg.services.StudentServiceImpl;

@SpringBootTest
public class StudentTest {

	@InjectMocks
	private IStudentService studentService = new StudentServiceImpl();
	@Mock
	private IStudentDao studentDao;

	Logger logger = LoggerFactory.getLogger(StudentTest.class);
	StudentDTO studentDto;
	Student student;

	@BeforeEach
	public void init() {
		studentDto = new StudentDTO();
		student = new Student();
		studentDto.setName("Test student");
		studentDto.setEmail("mock@test.com");
		studentDto.setDob(LocalDate.of(2001, 07, 02));
		studentDto.setGender("female");
		studentDto.setGuardianName("Test student guardian");
		studentDto.setMobile("8013206937");
		studentDto.setAddress("Kolkata");

		student.setId(101);
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setDob(studentDto.getDob());
		student.setGender(studentDto.getGender());
		student.setGuardianName(studentDto.getGuardianName());
		student.setMobile(studentDto.getMobile());
		student.setAddress(studentDto.getAddress());
	}

	@Test
	@DisplayName("Add student test case")
	public void addStudentTest() {

		when(studentDao.save(any(Student.class))).thenReturn(student);

		assertEquals(student, studentService.addStudent(studentDto));
	}

	@Test
	@DisplayName("View all student test case")
	public void viewAllStudentTest() throws StudentNotFoundException {
		when(studentDao.findAll()).thenReturn(Stream.of(student).collect(Collectors.toList()));
		assertEquals(1, studentService.getStudents().size());
	}

	@Test
	@DisplayName("View all student negative test case")
	public void viewAllStudentTestNegative() throws StudentNotFoundException {
		when(studentDao.findAll()).thenReturn(Stream.of(student).collect(Collectors.toList()));
		assertNotEquals(2, studentService.getStudents().size());
	}

//	@Test
//	@DisplayName("Remove student test case")
//	public void removeStudentByIdTest() {
//
//	}

	@Test
	@DisplayName("View student by id test case")
	public void viewStudentByIdTest() throws StudentNotFoundException {
		when(studentDao.findById(student.getId())).thenReturn(Optional.of(student));
		assertEquals(student, studentService.getStudentById(101));
	}
//
	@Test
	@DisplayName("View student by id negative test case")
	public void viewStudentByIdTestNegative() throws StudentNotFoundException {
		when(studentDao.findById(student.getId())).thenReturn(Optional.of(student));
		Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(102));
	}

//	@Test
//	@DisplayName("View student by name test case")
//	public void viewStudentBySearchedNameTest() {
//
//	}

//	@Test
//	@DisplayName("View student by mobile number test case")
//	public void viewStudentBYMobileNumberTest() {
//
//	}
}
