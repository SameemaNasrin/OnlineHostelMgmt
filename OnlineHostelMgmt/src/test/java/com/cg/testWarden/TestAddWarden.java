package com.cg.testWarden;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
import com.cg.dto.WardenDto;
import com.cg.entities.Hostel;
import com.cg.entities.Warden;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.services.IWardenService;
import com.cg.services.WardenServiceImpl;

@SpringBootTest
public class TestAddWarden {

	@Mock
	private IWardenDao wardenDao;
	@Mock
	private IHostelDao hostelDao;
	@InjectMocks
	private IWardenService service = new WardenServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		
		Hostel hostel1=new Hostel();
		hostel1.setId(5001);
		Warden warden1=new Warden();
		warden1.setId(101);
		warden1.setHostel(hostel1);
		List<Warden> lstwarden=new ArrayList<>();
		lstwarden.add(warden1);
		
		when(hostelDao.findById(5001)).thenReturn(Optional.of(hostel1));
		when(hostelDao.findById(5002)).thenReturn(Optional.empty());
		Warden warden=new Warden();
		warden.setId(1001);
		when(wardenDao.save(any(Warden.class))).thenReturn(warden);
	}
	@Test
	@DisplayName("test 1")
	public void addWardenTest()throws HostelNotFoundException, EmailAlreadyExistException{
		WardenDto wardenDto=new WardenDto();
		wardenDto.setHostel(5001);
		assertNotNull(service.addWarden(wardenDto));
	}
	@Test
	@DisplayName("test 2")
	public void addWardenTest1()throws HostelNotFoundException{
		WardenDto wardenDto=new WardenDto();
		wardenDto.setHostel(5002);
		assertThrows(HostelNotFoundException.class,()->service.addWarden(wardenDto));
	}
}