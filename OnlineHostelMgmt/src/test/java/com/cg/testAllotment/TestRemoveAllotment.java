package com.cg.testAllotment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IRoomDao;
import com.cg.entities.Allotment;
import com.cg.entities.Room;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.services.AllotmentServiceImpl;
import com.cg.services.IAllotmentService;

@SpringBootTest
public class TestRemoveAllotment {

	@InjectMocks
	private IAllotmentService service = new AllotmentServiceImpl();
	@Mock
	private IAllotmentDao allotmentDao;
	@Mock
	private IRoomDao roomDao;
	@Mock
	private IFeeStructureDao feeDao;
	
	@BeforeEach
	public void beforeEach()throws AllotmentNotFoundException,RoomNotFoundException {
	
		Room room=new Room();
		room.setRoomId(100);
		room.setMaximumSize(3);
		Allotment allotment1=new Allotment();
		allotment1.setRoom(room);
		Allotment allotment2=new Allotment();
		Optional<Allotment> optallotment = Optional.of(allotment1);
		Optional<Allotment> optallotment1 = Optional.of(allotment2);
		Optional<Allotment> optallotment2 = Optional.empty();
		when(allotmentDao.findById(1001)).thenReturn(optallotment);
		when(allotmentDao.findById(1002)).thenReturn(optallotment1);
		when(allotmentDao.findById(1003)).thenReturn(optallotment2);	

	}
	@Test
	@DisplayName("test 1")
	public void removeAllotmentTest()throws AllotmentNotFoundException,RoomNotFoundException{
		assertNotNull(service.removeAllotment(1001));
	}
	@Test
	@DisplayName("test 2")
	public void removeAllotmentTest1()throws AllotmentNotFoundException,RoomNotFoundException{
		assertThrows(RoomNotFoundException.class, ()->service.removeAllotment(1002));
	}
	@Test
	@DisplayName("test 3")
	public void removeAllotmentTest2()throws AllotmentNotFoundException,RoomNotFoundException{
		assertThrows(AllotmentNotFoundException.class, ()->service.removeAllotment(1003));
	}
	
	}
	

