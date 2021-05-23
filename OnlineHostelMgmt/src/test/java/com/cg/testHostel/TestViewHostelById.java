package com.cg.testHostel;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
class TestViewHostelById {

	@Mock
	IHostelDao dao;
	
	@InjectMocks
	IHostelService service = new HostelServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		Optional<Hostel> optHostel1 = Optional.of(new Hostel());
		Optional<Hostel> optHostel2 = Optional.empty();
	
		when(dao.findById(101)).thenReturn(optHostel1);
		when(dao.findById(102)).thenReturn(optHostel2);
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
