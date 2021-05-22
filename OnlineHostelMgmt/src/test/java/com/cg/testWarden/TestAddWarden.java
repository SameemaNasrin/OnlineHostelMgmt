package com.cg.testWarden;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IHostelDao;
import com.cg.dao.IWardenDao;
import com.cg.services.IWardenService;
import com.cg.services.WardenServiceImpl;

@SpringBootTest
public class TestAddWarden{
	
	@Mock
	private IWardenDao wardenDao;
	@Mock
	private IHostelDao hostelDao;
	@InjectMocks
	private IWardenService service=new WardenServiceImpl();
	@BeforeEach
	public void beforeEach() {
		
		
	}
}