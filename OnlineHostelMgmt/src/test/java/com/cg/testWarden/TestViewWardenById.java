package com.cg.testWarden;

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

import com.cg.dao.IWardenDao;
import com.cg.entities.Warden;
import com.cg.exceptions.WardenNotFoundException;
import com.cg.services.IWardenService;
import com.cg.services.WardenServiceImpl;

@SpringBootTest
public class TestViewWardenById {
	
	@Mock
	private IWardenDao dao;
	@InjectMocks
	private IWardenService service = new WardenServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		
		Optional<Warden> optWarden1 = Optional.of(new Warden());
		Optional<Warden> optWarden2 = Optional.empty();
	
		when(dao.findById(29)).thenReturn(optWarden1);
		when(dao.findById(30)).thenReturn(optWarden2);
	}
	
	@Test
	@DisplayName(value = "test view by Id 29")
	public void testViewById1() throws WardenNotFoundException  {
		assertNotNull(service.viewWardenByWardenId((29)));
	}
	
	@Test
	@DisplayName(value = "negative test view by Id 30")
	public void testViewById2() {
		assertThrows(WardenNotFoundException.class, ()->service.viewWardenByWardenId(30));
	}
	

}
