package com.cg.testHostel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IHostelDao;
import com.cg.dto.HostelDto;
import com.cg.entities.Hostel;
import com.cg.services.HostelServiceImpl;
import com.cg.services.IHostelService;

@SpringBootTest
class TestAddHostel {

	@Mock
	private IHostelDao dao;

	@InjectMocks
	private IHostelService service = new HostelServiceImpl();

	HostelDto dto;
	Hostel hostel;

	@BeforeEach
	public void beforeEach() {
		dto = new HostelDto();
		hostel = new Hostel(1, "techno hostel", "9877627162", "girls", "kolkata, west bengal", 32000.0, null);
		when(dao.save(any(Hostel.class))).thenReturn(hostel);
	}

	@Test
	@DisplayName("Test for add hostel")
	void testAddHostel() {
		assertEquals(1, service.addHostel(dto));
	}

}
