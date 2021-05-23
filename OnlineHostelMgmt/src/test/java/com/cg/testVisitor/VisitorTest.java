package com.cg.testVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import com.cg.dao.IHostelDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IVisitorDao;
import com.cg.dto.VisitorDTO;
import com.cg.entities.Hostel;
import com.cg.entities.Student;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.IVisitorService;
import com.cg.services.VisitorServiceImpl;

public class VisitorTest {
	@InjectMocks
	private IVisitorService visitorService = new VisitorServiceImpl();
	
	@Mock
	private IVisitorDao visitorDao;
	@Mock
	private IHostelDao hostelDao;
	@Mock
	private IStudentDao studentDao;
	
	VisitorDTO visitorDto;
	
	Visitor visitor;
	
	Hostel hostel;
	
	Student student;
	
	@BeforeEach
	public void init() {
		hostel = new Hostel();
		hostel.setId(101);
		hostel.setName("Test hostel 1");
		hostel.setAddress("Pune");
		hostel.setContact("1231231234");
		hostel.setFee(1200.0);
		hostel.setType("boys");
		
		student = new Student();
		student.setId(201);
		student.setName("Supriyo Das");
		student.setAddress("Kolkata");
		student.setDob(LocalDate.of(1998, 9, 15));
		student.setMobile("8013206937");
		student.setEmail("supriyo.das@gmail.com");
		student.setGender("male");
		student.setGuardianName("Samir Das");
		
		visitorDto = new VisitorDTO();
		visitorDto.setDateOfVisiting(LocalDate.of(2021, 02, 23));
		visitorDto.setName("Visitor test 1");
		visitorDto.setNumber("9051020414");
		visitorDto.setReason("Normal visit");
		visitorDto.setStudentRelation("Sister");
		visitorDto.setVisitorAddress("Mumbai");
		visitorDto.setHostelId(hostel.getId());
		visitorDto.setStudentId(student.getId());
		visitor = new Visitor();
		visitor.setVisitorName(visitorDto.getName());
		visitor.setContactNumber(visitorDto.getNumber());
		visitor.setReason(visitorDto.getReason());
		visitor.setStudentRelation(visitorDto.getStudentRelation());
		visitor.setVisitorAddress(visitorDto.getVisitorAddress());
		visitor.setHostel(hostel);
		visitor.setStudent(student);
		
		when(studentDao.findById(student.getId())).thenReturn(Optional.of(student));
		when(visitorDao.save(any(Visitor.class))).thenReturn(visitor);
	}
	
	@Test
	@DisplayName("Add new visitor test case")
	public void addVisitorTest() throws StudentNotFoundException, HostelNotFoundException {
		assertEquals(visitor, visitorService.addVisitor(visitorDto));
	}
	
	@Test
	@DisplayName("Add new visitor negative test case-student not found")
	public void addVisitorNegativeTestWithStudent() throws StudentNotFoundException, HostelNotFoundException {
		visitorDto.setStudentId(202);
		Assertions.assertThrows(StudentNotFoundException.class, ()->visitorService.addVisitor(visitorDto));
	}
	
	@Test
	@DisplayName("Add new visitor negative test case-hostel not found")
	public void addVisitorNegativeTestWithHostel() throws StudentNotFoundException, HostelNotFoundException {
		visitorDto.setHostelId(102);
		Assertions.assertThrows(HostelNotFoundException.class, ()->visitorService.addVisitor(visitorDto));
	}

}
