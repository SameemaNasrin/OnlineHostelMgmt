package com.cg.testHostel;

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
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.services.HostelServiceImpl;
import com.cg.services.IHostelService;



@SpringBootTest
public class TestViewAllHostel {

	@Mock
	private IHostelDao dao;
	
	@InjectMocks
	private IHostelService service = new HostelServiceImpl();
	
	List<Hostel> list1 = new ArrayList<>();
	List<Hostel> list2 = new ArrayList<>();
	
	
	@BeforeEach
	public void beforeEach(){
		
		list1.add(new Hostel(1, "NHSM Hostel", "8918273646", "girls", "kolkata, west bengal", 32000.0, null));
		list1.add(new Hostel(2, "GNIT Hostel", "9898172634", "boys", "sodepur, west bengal", 30000.0, null));
		list1.add(new Hostel(3, "Techno Hostel", "9991723361", "girls", "kolkata, west bengal", 42000.0, null));		
	}
	
	@Test
	@DisplayName("Test for view all hostel")
	public void testViewAllHostel1() throws HostelNotFoundException {
		when(dao.findAll()).thenReturn(list1);
		assertTrue(service.viewAllHostel().size()>0);
	}
	
	@Test
	@DisplayName("Negative Test for view all hostel")
	public void testViewAllHostel2() throws HostelNotFoundException {
		when(dao.findAll()).thenReturn(list2);
		assertThrows(HostelNotFoundException.class, ()-> service.viewAllHostel());
	}
}
