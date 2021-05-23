package com.cg.testFeeStructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.FeeStructure;
import com.cg.entities.Student;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.FeeStructureServiceImpl;
import com.cg.services.IFeeStructService;


@SpringBootTest
class TestViewFeeByStudentId {
	
	@Mock
	IFeeStructureDao feeStructureDao;
	
	@Mock
	IStudentDao studentDao;
	
	@InjectMocks
	IFeeStructService service = new FeeStructureServiceImpl();
	
	Student student, student1;
	FeeStructure feeStructure;
	
	@BeforeEach
	public void beforeEach() {
		student = new Student();
		student.setId(1);
		
		student1 = new Student();
		student.setId(3);
		
		feeStructure = new FeeStructure();
		
		when(studentDao.findById(1)).thenReturn(Optional.of(student));
		when(studentDao.findById(3)).thenReturn(Optional.of(student1));
		
		when(feeStructureDao.getFeeStructure(1)).thenReturn(feeStructure);
	}
	
	@Test
	void testViewFeeStructureByStudentId1() throws StudentNotFoundException, FeeStructureNotFoundException {
		assertEquals(feeStructure, service.viewFeeByStudentId(1).get(0));
	}
	
	@Test
	void testViewFeeStructureByStudentId2() throws StudentNotFoundException, FeeStructureNotFoundException {
		assertThrows(StudentNotFoundException.class, () -> service.viewFeeByStudentId(2).get(0));
	}
	
	@Test
	void testViewFeeStructureByStudentId3() throws StudentNotFoundException, FeeStructureNotFoundException {
		assertThrows(FeeStructureNotFoundException.class , () -> service.viewFeeByStudentId(3).get(0));
	}
}
