package com.cg.testFeeStructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IFeeStructureDao;
import com.cg.entities.FeeStructure;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.services.FeeStructureServiceImpl;
import com.cg.services.IFeeStructService;

@SpringBootTest
public class TestViewAllDefaulter {

	@Mock
	IFeeStructureDao dao;
	
	@InjectMocks
	IFeeStructService service = new FeeStructureServiceImpl();
	
	List<FeeStructure> defaulters, noDefaulter;
	FeeStructure feeStructure;
	
	@BeforeEach
	public void beforeEach() {
		defaulters = new ArrayList<FeeStructure>();
		feeStructure = new FeeStructure();
		feeStructure.setPaymentStatus("not paid");
		defaulters.add(new FeeStructure());
		defaulters.add(new FeeStructure());
		defaulters.add(new FeeStructure());
		when(dao.findByPaymentStatus("not paid")).thenReturn(defaulters);
		System.out.println(defaulters);
		
		noDefaulter = new ArrayList<>();
	}
	
	@Test
	@DisplayName("Test for view all defaulters")
	void testViewAllDefaulter1() throws FeeStructureNotFoundException {
		assertEquals(defaulters, service.viewAllDefaulter());
	}
	
	@Test
	@DisplayName("Test for FeeStructureNotFoundException")
	void testViewAllDefaulter2() throws FeeStructureNotFoundException {
		when(dao.findByPaymentStatus("not paid")).thenReturn(noDefaulter);
		assertThrows(FeeStructureNotFoundException.class, () -> service.viewAllDefaulter());
	}
}
