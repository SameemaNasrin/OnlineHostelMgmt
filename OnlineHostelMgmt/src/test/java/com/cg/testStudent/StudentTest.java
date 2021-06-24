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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.test.context.SpringBootTest;
import com.cg.dao.IStudentDao;
import com.cg.dto.StudentDTO;
import com.cg.entities.Student;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.MobileNumberAlreadyExistsException;
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

		when(studentDao.save(any(Student.class))).thenReturn(student);
		when(studentDao.findAll()).thenReturn(Stream.of(student).collect(Collectors.toList()));
		when(studentDao.findById(student.getId())).thenReturn(Optional.of(student));
		when(studentDao.findByMobile(student.getMobile())).thenReturn(Stream.of(student).collect(Collectors.toList()));
		when(studentDao.findByNameContaining(student.getName()))
				.thenReturn(Stream.of(student).collect(Collectors.toList()));
	}

	@Test
	@DisplayName("Add student test case")
	public void addStudentTest() throws EmailAlreadyExistException, MobileNumberAlreadyExistsException {
		assertEquals(student, studentService.addStudent(studentDto));
	}

	@Test
	@DisplayName("View all student test case")
	public void viewAllStudentTest() throws StudentNotFoundException {
		assertEquals(1, studentService.getStudents().size());
	}

	@Test
	@DisplayName("View all student negative test case")
	public void viewAllStudentTestNegative() throws StudentNotFoundException {
		List<Student> list = new ArrayList<>();
		when(studentDao.findAll()).thenReturn(list);
		Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getStudents());

	}

	@Test
	@DisplayName("Remove student test case")
	public void removeStudentByIdTest() throws StudentNotFoundException {
		studentService.removeStudentById(student.getId());
		verify(studentDao, times(1)).delete(student);
	}

	@Test
	@DisplayName("Remove student negative test case")
	public void removeStudentByIdNegative() throws StudentNotFoundException {
		Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.removeStudentById(102));
	}

	@Test
	@DisplayName("View student by id test case")
	public void viewStudentByIdTest() throws StudentNotFoundException {
		assertEquals(student, studentService.getStudentById(101));
	}

	@Test
	@DisplayName("View student by id negative test case")
	public void viewStudentByIdTestNegative() throws StudentNotFoundException {
		Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(102));
	}

	@Test
	@DisplayName("View student by name test case")
	public void viewStudentBySearchedNameTest() throws StudentNotFoundException {
		assertEquals(student.getName(), studentService.getStudentByName(student.getName()).get(0).getName());
	}
	
	@Test
	@DisplayName("View student by name negative test case")
	public void viewStudentBySearchedNameNegativeTest() throws StudentNotFoundException {
		List<Student> list = new ArrayList<>();
		when(studentDao.findByNameContaining(student.getName())).thenReturn(list);
		Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getStudentByName(student.getName()));
	}

	@Test
	@DisplayName("View student by mobile number test case")
	public void viewStudentBYMobileNumberTest() throws StudentNotFoundException {
		assertEquals(student, studentService.getStudentByMobileNumber(student.getMobile()));
	}
	
	@Test
	@DisplayName("View student by mobile number negative test case")
	public void viewStudentBYMobileNumberNegativeTest() throws StudentNotFoundException {
		Assertions.assertThrows(StudentNotFoundException.class, ()->studentService.getStudentByMobileNumber("8013206938"));
	}
	
}
