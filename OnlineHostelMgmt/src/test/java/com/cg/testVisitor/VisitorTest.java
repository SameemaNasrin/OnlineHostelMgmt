package com.cg.testVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IHostelDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IVisitorDao;
import com.cg.dto.VisitorDTO;
import com.cg.entities.Hostel;
import com.cg.entities.Student;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotAllottedException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.VisitorNotFoundException;
import com.cg.services.IVisitorService;
import com.cg.services.VisitorServiceImpl;

@SpringBootTest
public class VisitorTest {

	@Mock
	IVisitorDao visitorDao;

	@Mock
	IStudentDao studentDao;

	@Mock
	IHostelDao hostelDao;

	@InjectMocks
	IVisitorService service = new VisitorServiceImpl();

	Visitor visitor;
	VisitorDTO visitorDto;
	Student student;
	Hostel hostel;

	@BeforeEach
	void beforeEach() {
		visitor = new Visitor();
		visitorDto = new VisitorDTO();
		visitorDto.setStudentId(1);
//		visitorDto.setHostelId(1001);
		visitorDto.setDateOfVisiting(LocalDate.of(2021, 02, 23));
		visitorDto.setName("Visitor test 1");
		visitorDto.setNumber("9051020414");
		visitorDto.setReason("Normal visit");
		visitorDto.setStudentRelation("Sister");
		visitorDto.setVisitorAddress("Mumbai");

		student = new Student();
		student.setId(1);
		student.setName("Supriyo Das");
		student.setAddress("Kolkata");
		student.setDob(LocalDate.of(1998, 9, 15));
		student.setMobile("8013206937");
		student.setEmail("supriyo.das@gmail.com");
		student.setGender("male");
		student.setGuardianName("Samir Das");

		hostel = new Hostel();
		hostel.setId(1001);
		hostel.setName("Test hostel 1");
		hostel.setAddress("Pune");
		hostel.setContact("1231231234");
		hostel.setFee(1200.0);
		hostel.setType("boys");

		when(studentDao.findById(student.getId())).thenReturn(Optional.of(student));
		when(hostelDao.findById(hostel.getId())).thenReturn(Optional.of(hostel));
		when(visitorDao.save(any(Visitor.class))).thenReturn(visitor);
		when(visitorDao.findByDateOfVisiting(visitorDto.getDateOfVisiting()))
				.thenReturn(Stream.of(visitor).collect(Collectors.toList()));

	}

	@Test
	@DisplayName("Test for add visitor")
	void addStudentTestCase() throws StudentNotFoundException, HostelNotFoundException, StudentNotAllottedException {
		assertEquals(visitor, service.addVisitor(visitorDto));
	}

	@Test
	@DisplayName("Negative test case for add visitor-student not found")
	void addStudentNegativeTestCaseStudent() throws StudentNotFoundException, HostelNotFoundException {
		visitorDto.setStudentId(2);
		assertThrows(StudentNotFoundException.class, () -> service.addVisitor(visitorDto));
	}

	@Test
	@DisplayName("Negative test for add visitor-hostel not found")
	void addStudentNegativeTestCaseHostel() throws StudentNotFoundException, HostelNotFoundException {
//		visitorDto.setHostelId(1002);
		assertThrows(HostelNotFoundException.class, () -> service.addVisitor(visitorDto));
	}

	@Test
	@DisplayName("View visitor by visit date test case")
	void viewVisitorByVisitDateTestCase() throws VisitorNotFoundException {
		assertEquals(1, service.getVisitorByVisitDate(visitorDto.getDateOfVisiting()).size());
	}
	
	@Test
	@DisplayName("View visitor by visit date negative test case")
	void viewVisitorByVisitDateNegativeTestCase() throws VisitorNotFoundException {
		Assertions.assertThrows(VisitorNotFoundException.class, ()->service.getVisitorByVisitDate(LocalDate.now()));
	}

}
