package com.cg.testHostel;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cg.dao.IHostelDao;
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.services.HostelServiceImpl;
import com.cg.services.IHostelService;


//doesnot work
public class TestViewAllHostel {

	@Mock
	private IHostelDao dao;
	
	@InjectMocks
	private IHostelService service = new HostelServiceImpl();
	
	@BeforeEach
	public void beforeEach(){
		List<Hostel> list1 = new ArrayList<>();
		list1.add(new Hostel((long)1, "NHSM Hostel", "8918273646", "girls", "kolkata, west bengal", 32000.0));
		list1.add(new Hostel((long)2, "GNIT Hostel", "9898172634", "boys", "sodepur, west bengal", 30000.0));
		list1.add(new Hostel((long)3, "Techno Hostel", "9991723361", "girls", "kolkata, west bengal", 42000.0));
		
		when(dao.findAll()).thenReturn(list1);
	}
	
	@Test
	@DisplayName("Test for view all hostel")
	public void testViewAllHostel() throws HostelNotFoundException {
		assertTrue(service.viewAllHostel().size()>0);
	}
}
