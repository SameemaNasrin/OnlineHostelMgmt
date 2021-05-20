package com.cg.testWarden;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IHostelDao;
import com.cg.dao.IWardenDao;
import com.cg.entities.Hostel;
import com.cg.entities.Warden;
import com.cg.exceptions.WardenNotFoundException;
import com.cg.services.IWardenService;
import com.cg.services.WardenServiceImpl;

@SpringBootTest
public class TestViewAllWarden {
	
	@Mock
	private IWardenDao dao;
	@Mock
	private IHostelDao dao1;
	@InjectMocks
	private IWardenService service=new WardenServiceImpl();
	
	List<Hostel> list1 = new ArrayList<>();
	List<Warden> list2 = new ArrayList<>();
	List<Warden> list3 = new ArrayList<>();
	
	@BeforeEach
	public void beforeEach(){
		
		list1.add(new Hostel((long)1, "NHSM Hostel", "8918273646", "girls", "kolkata, west bengal", 32000.0, null));
		list1.add(new Hostel((long)2, "GNIT Hostel", "9898172634", "boys", "sodepur, west bengal", 30000.0, null));
		list1.add(new Hostel((long)3, "Techno Hostel", "9991723361", "girls", "kolkata, west bengal", 42000.0, null));	
		
		list2.add(new Warden(11,"rahul21@yahoo.com","Rahul",(list1.get(0))));
		list2.add(new Warden(12,"rakesh15@gmail.com","Rakesh",(list1.get(1))));
		list2.add(new Warden(13,"ajay93@yahoo.com","Ajay",(list1.get(2))));
	}
	
	@Test
	@DisplayName("Test for view all wardens")
	public void testViewAllWarden1() throws WardenNotFoundException {
		when(dao.findAll()).thenReturn(list2);
		assertTrue(service.viewAllWarden().size()>0);
	}
	
	@Test
	@DisplayName("Negative Test for view all hostel")
	public void testViewAllHostel2() throws WardenNotFoundException {
		when(dao.findAll()).thenReturn(list3);
		assertThrows(WardenNotFoundException.class, ()-> service.viewAllWarden());
	}
	

}
