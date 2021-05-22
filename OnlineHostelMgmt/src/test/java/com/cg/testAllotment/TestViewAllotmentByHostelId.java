package com.cg.testAllotment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.Allotment;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.services.AllotmentServiceImpl;
import com.cg.services.IAllotmentService;

//NotWorking
@SpringBootTest
public class TestViewAllotmentByHostelId {
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
	public void beforeEach() throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException {
		
		List<Allotment> lstallotment1=new ArrayList<>();
		lstallotment1.add(new Allotment());
		Hostel hostel=new Hostel();
		Room room1=new Room();
		room1.setRoomId(1);
		Set<Room> rooms = new HashSet<>();
		rooms.add(room1);
		hostel.setRooms(rooms);
		Hostel hostel2=new Hostel();
		Room room2=new Room();
		room2.setRoomId(2);
		Set<Room> roomset2=new HashSet<>();
		roomset2.add(room2);
		hostel2.setRooms(roomset2);
		Optional<Hostel> opthostel = Optional.of(hostel);
		Optional<Hostel> opthostel2 = Optional.empty();
		Optional<Hostel> opthostel3=Optional.of(hostel2);
		when(hostelDao.findById(1)).thenReturn(opthostel);
		when(hostelDao.findById(2)).thenReturn(opthostel2);
		when(hostelDao.findById(3)).thenReturn(opthostel3);
		when(allotmentDao.findByRoom(room1)).thenReturn(lstallotment1);
		when(allotmentDao.findByRoom(room2)).thenReturn(new ArrayList<Allotment>());
	
	}

	@Test
	@DisplayName("Test Case for viewing Allotment for hostelId 1")
	public void viewAllotmentTest() throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException {
		assertTrue(service.viewAllotmentByHostelId(1).size() > 0);

	}

	@Test
	@DisplayName("Negative Test Case for viewing Allotment for hostelId 2")
	public void viewAllotmentTest1() throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException {
		assertThrows(HostelNotFoundException.class, () -> service.viewAllotmentByHostelId(2));
	}

	@Test
	@DisplayName("Negative Test Case for viewing Allotment for no allotment registered")
	public void viewAllotmentTest3() throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException {
		assertThrows(AllotmentNotFoundException.class, () -> service.viewAllotmentByHostelId(3));
	}

}
