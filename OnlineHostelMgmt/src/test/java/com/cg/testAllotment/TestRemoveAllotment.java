package com.cg.testAllotment;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
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
		/*Room room1=new Room();
		room1.setRoomId(101);
		room1.setMaximumSize(4);
		Allotment allotment1=new Allotment();
		allotment1.setId(1);
		allotment1.setRoom(room1);
		List<Allotment> lstallotment=new ArrayList<>();
		lstallotment.add(allotment1);
		*/
		
		Optional<Allotment> allot1 = Optional.of(new Allotment());
		when(allotmentDao.findById(1001)).thenReturn(allot1);
		when(allotmentDao.findById(1003)).thenReturn(allot1);
		Optional<Allotment> allot2 = Optional.empty();
		when(allotmentDao.findById(1002)).thenReturn(allot2);
		Room room=new Room();
		room.setRoomId(100);
		room.setMaximumSize(3);
		
		
		
		
	}
}
