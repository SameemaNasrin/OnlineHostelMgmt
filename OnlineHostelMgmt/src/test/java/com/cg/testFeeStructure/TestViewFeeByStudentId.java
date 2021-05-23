package com.cg.testFeeStructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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


//doesnot work--- as in the service return type is List<FeeStructure> 
@SpringBootTest
public class TestViewFeeByStudentId {
	
	@Mock
	IFeeStructureDao feeStructureDao;
	
	@Mock
	IStudentDao studentDao;
	
	@InjectMocks
	IFeeStructService service = new FeeStructureServiceImpl();
	
	Student student;
	FeeStructure feeStructure;
	
	@BeforeEach
	public void beforeEach() {
		student = new Student();
		student.setId(1);
		
		feeStructure = new FeeStructure();
		
		when(studentDao.findById(1)).thenReturn(Optional.of(student));
		when(feeStructureDao.getFeeStructure(1)).thenReturn(feeStructure);
	}
	
	@Test
	public void testViewFeeStructureByStudentId1() throws StudentNotFoundException, FeeStructureNotFoundException {
		assertEquals(feeStructure, service.viewFeeByStudentId(1));
	}
}
