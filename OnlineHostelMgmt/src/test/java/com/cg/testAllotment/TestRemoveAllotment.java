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
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.Allotment;
import com.cg.entities.Room;
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
	private IHostelDao hostelDao;
	@Mock
	private IStudentDao studentDao;
	
	@BeforeEach
	public void beforeEach() {
		Room room1=new Room();
		room1.setRoomId(101);
		room1.setMaximumSize(4);
		Allotment allotment1=new Allotment();
		allotment1.setId(1);
		allotment1.setRoom(room1);
		List<Allotment> lstallotment=new ArrayList<>();
		lstallotment.add(allotment1);
		

		
		
		
		
	}
}
