package com.cg.testHostel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import com.cg.dto.HostelDto;
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.services.HostelServiceImpl;
import com.cg.services.IHostelService;

@SpringBootTest
class HostelTest {

	@Mock
	private IHostelDao dao;

	@InjectMocks
	private IHostelService service = new HostelServiceImpl();

	HostelDto dto;
	Hostel hostel;
	
	List<Hostel> list1 = new ArrayList<>();
	List<Hostel> list2 = new ArrayList<>();

	@BeforeEach
	public void beforeEach() {
		dto = new HostelDto();
		hostel = new Hostel(1, "techno hostel", "9877627162", "girls", "kolkata, west bengal", 32000.0, null);
		when(dao.save(any(Hostel.class))).thenReturn(hostel);
		
		list1.add(new Hostel(1, "NHSM Hostel", "8918273646", "girls", "kolkata, west bengal", 32000.0, null));
		list1.add(new Hostel(2, "GNIT Hostel", "9898172634", "boys", "sodepur, west bengal", 30000.0, null));
		list1.add(new Hostel(3, "Techno Hostel", "9991723361", "girls", "kolkata, west bengal", 42000.0, null));
		
		Optional<Hostel> optHostel1 = Optional.of(new Hostel());
		Optional<Hostel> optHostel2 = Optional.empty();
	
		when(dao.findById(101)).thenReturn(optHostel1);
		when(dao.findById(102)).thenReturn(optHostel2);
	
	}

	@Test
	@DisplayName("Test for add hostel")
	void testAddHostel() {
		assertEquals(1, service.addHostel(dto));
	}
	
	@Test
	@DisplayName("Test for view all hostel")
	void testViewAllHostel1() throws HostelNotFoundException {
		when(dao.findAll()).thenReturn(list1);
		assertTrue(service.viewAllHostel().size()>0);
	}
	
	@Test
	@DisplayName("Negative Test for view all hostel")
	void testViewAllHostel2() throws HostelNotFoundException {
		when(dao.findAll()).thenReturn(list2);
		assertThrows(HostelNotFoundException.class, ()-> service.viewAllHostel());
	}

	@Test
	@DisplayName(value = "Test for view by Id 101")
	void testViewById1() throws HostelNotFoundException  {
		assertNotNull(service.viewHostelById(101));
	}
	
	@Test
	@DisplayName(value = "Negative test for view by Id 102")
	void testViewById2() {
		assertThrows(HostelNotFoundException.class, ()->service.viewHostelById(102));
	}
}
