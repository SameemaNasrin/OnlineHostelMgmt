package com.cg.testVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

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
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.IVisitorService;
import com.cg.services.VisitorServiceImpl;

@SpringBootTest
class VisitorTest1 {

	@Mock
	IVisitorDao visitorDao;
	
	@Mock
	IStudentDao studentDao;
	
	@Mock
	IHostelDao hostelDao;
	
	@InjectMocks
	IVisitorService service = new VisitorServiceImpl();
	
	Visitor visitor;
	VisitorDTO visitorDto, visitorDto1, visitorDto3;
	Student student;
	Hostel hostel;
	
	@BeforeEach
	void beforeEach() {
		visitor = new Visitor();
		visitorDto = new VisitorDTO();
		visitorDto.setStudentId(1);
		visitorDto.setHostelId(1001);
		student = new Student();
		student.setId(1);
		hostel = new Hostel();
		hostel.setId(1001);
		
		when(studentDao.findById(1)).thenReturn(Optional.of(student));
		when(hostelDao.findById(1001)).thenReturn(Optional.of(hostel));
		when(visitorDao.save(any(Visitor.class))).thenReturn(visitor);
		
		visitorDto1 = new VisitorDTO();
		
		visitorDto3 = new VisitorDTO();
		visitorDto3.setStudentId(1);
	}
	
	@Test
	@DisplayName("Test for add visitor")
	void testAddVisitor1() throws StudentNotFoundException, HostelNotFoundException {
		assertEquals(visitor, service.addVisitor(visitorDto));
	}
	
	@Test
	@DisplayName("Negative test for add visitor")
	void testAddVisitor2() throws StudentNotFoundException, HostelNotFoundException {
		assertThrows(StudentNotFoundException.class, () -> service.addVisitor(visitorDto1));
	}
	
	@Test
	@DisplayName("Negative test for add visitor")
	void testAddVisitor3() throws StudentNotFoundException, HostelNotFoundException {
		assertThrows(HostelNotFoundException.class, () -> service.addVisitor(visitorDto3));
	}
}
