	package com.cg.testFeeStructure;

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

import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.FeeStructure;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.IncorrectAmountException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.FeeStructureServiceImpl;
import com.cg.services.IFeeStructService;

@SpringBootTest
public class TestPayFeeByStudentId {

	@Mock
	IFeeStructureDao feeStructureDao;
	
	@Mock
	IStudentDao studentDao;
	
	@InjectMocks
	IFeeStructService service = new FeeStructureServiceImpl();
	
	Student student1, student2, student3;
	FeeStructure feeStructure, feeStructure3;
	
	@BeforeEach
	public void beforeEach() {
		student1 = new Student();
		student1.setId(101);
		feeStructure = new FeeStructure();
		feeStructure.setId(10);
		feeStructure.setTotalFees(32000.0);
		when(studentDao.findById(101)).thenReturn(Optional.of(student1));
		when(feeStructureDao.findByStudentId(101)).thenReturn(feeStructure);
		
		
		student3 = new Student();
		student3.setId(103);
		feeStructure3 = new FeeStructure();
		feeStructure3.setId(13);
		feeStructure3.setTotalFees(30000.0);
		when(studentDao.findById(103)).thenReturn(Optional.of(student3));
		when(feeStructureDao.findByStudentId(103)).thenReturn(feeStructure3);
		
		when(feeStructureDao.save(any(FeeStructure.class))).thenReturn(feeStructure);

		
	}
	
	@Test
	@DisplayName("Test for pay fee by student id 101")
	void testPayFeeByStudentId1() throws StudentNotFoundException, IncorrectAmountException, AllotmentNotFoundException {
		
		assertEquals(10, service.payFeeByStudentId(101, 32000.0));
	}
	
	@Test
	@DisplayName("test for StudentNotFoundException")
	void testPayFeeByStudentId2() {
		assertThrows(StudentNotFoundException.class, () -> service.payFeeByStudentId(102, 32000.0));
	}
	
	@Test
	@DisplayName("test for IncorrectAmountException")
	void testPayFeeByStudentId3() {
	
		System.out.println(feeStructure3);
		assertThrows(IncorrectAmountException.class, () -> service.payFeeByStudentId(103, 32000.0));
	}
}
