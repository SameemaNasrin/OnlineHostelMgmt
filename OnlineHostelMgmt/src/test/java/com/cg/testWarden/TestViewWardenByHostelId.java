package com.cg.testWarden;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.WardenNotFoundException;
import com.cg.services.IWardenService;
import com.cg.services.WardenServiceImpl;

@SpringBootTest
public class TestViewWardenByHostelId {
	@Mock
	private IWardenDao wardenDao;
	@Mock
	private IHostelDao hostelDao;
	@InjectMocks
	private IWardenService service = new WardenServiceImpl();
	
	@BeforeEach
	public void beforeEach() throws WardenNotFoundException,HostelNotFoundException{
		
		List<Warden> lst=new ArrayList<>();
		List<Warden> lst1 = new  ArrayList<>();
		Optional<Hostel> hostel1= Optional.of(new Hostel(1, "techno hostel", "9877627162", "girls", "kolkata, west bengal", 32000.0, null));
		Optional<Hostel> hostel2=Optional.empty();
		Optional<Hostel> hostel3= Optional.of(new Hostel(3, "NSHM hostel", "9877627165", "boys", "kolkata, west bengal", 22000.0, null));
		
		lst.add(new Warden(11,"rahul21@yahoo.com","Rahul",hostel1.get()));
		lst.add(new Warden(12,"rakesh21@yahoo.com","Rakesh",hostel1.get()));
		when(hostelDao.findById(1)).thenReturn(hostel1);
		when(hostelDao.findById(2)).thenReturn(hostel2);
		when(hostelDao.findById(3)).thenReturn(hostel3);
		when(wardenDao.findByHostelId(1)).thenReturn(lst);
		when(wardenDao.findByHostelId(3)).thenReturn(lst1);
	}
		@Test
		@DisplayName(value="Test for view warden for hostel id 1")
		
		public void testViewWarden1() throws WardenNotFoundException,HostelNotFoundException {
			
			assertTrue(service.viewWardenByHostelId(1).size()>0);
		}
		@Test
		@DisplayName(value="Test for view warden for hostel id 3")
		
		public void testViewWarden2()throws WardenNotFoundException,HostelNotFoundException {
			assertThrows(WardenNotFoundException.class, ()-> service.viewWardenByHostelId(3));
			}
		//not working
		@Test
		@DisplayName(value="Test for view warden for hostel id 2")
		public void testViewOrder3() throws WardenNotFoundException, HostelNotFoundException{
			assertThrows(HostelNotFoundException.class, ()->service.viewWardenByHostelId(2));
		}
		
	}
