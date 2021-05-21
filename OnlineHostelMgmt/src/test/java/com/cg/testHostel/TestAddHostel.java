package com.cg.testHostel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
public class TestAddHostel {

	@Mock
	private IHostelDao dao;

	@InjectMocks
	private IHostelService service = new HostelServiceImpl();

	HostelDto dto;
	Hostel hostel;

	@BeforeEach
	public void beforeEach() {
		dto = new HostelDto();
		hostel = new Hostel();

		dto.setName("Techno Hostel");
		dto.setContact("9887535142");
		dto.setType("girls");
		dto.setAddress("kolkata, west bengal");
		dto.setFee(3200.0);

		hostel.setId(1);
		hostel.setName(dto.getName());
		hostel.setContact(dto.getContact());
		hostel.setType(dto.getType());
		hostel.setAddress(dto.getAddress());
		hostel.setFee(dto.getFee());

		when(dao.save(any(Hostel.class))).thenReturn(hostel);
	}

	@Test
	@DisplayName("Test for add hostel")
	public void testAddHostel() {
		System.out.println(hostel);
		assertEquals(1, service.addHostel(dto));
	}

}
