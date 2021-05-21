package com.cg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dto.HostelDto;
import com.cg.dto.RoomDTO;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.services.IRoomService;
import com.cg.services.RoomServiceImpl;

@SpringBootTest
public class RoomTest {

	@InjectMocks
	IRoomService roomService = new RoomServiceImpl();

	@Mock
	IRoomDao roomDao;

	@Mock
	IHostelDao hostelDao;

	RoomDTO roomDto;

	Room room;

	HostelDto hostelDto;

	Hostel hostel;

	@BeforeEach
	public void init() {
		hostelDto = new HostelDto();
		hostelDto.setAddress("Test Address");
		hostelDto.setContact("Test Contact");
		hostelDto.setFee(2000.0);
		hostelDto.setName("Test Hostel name");
		hostelDto.setType("Test type");

		hostel = new Hostel();
		hostel.setName(hostelDto.getName());
		hostel.setContact(hostelDto.getContact());
		hostel.setType(hostelDto.getType());
		hostel.setAddress(hostelDto.getAddress());
		hostel.setFee(hostelDto.getFee());
		hostel.setId((201));

		roomDto = new RoomDTO();
		roomDto.setFloor(2);
		roomDto.setHostel_id(201);
		roomDto.setMaximumSize(3);
		roomDto.setRoomDesc("Test Room desc");
		roomDto.setRoomNo("301");

		room = new Room();
		room.setRoomNo(roomDto.getRoomNo());
		room.setFloor(roomDto.getFloor());
		room.setRoomDesc(roomDto.getRoomDesc());
		room.setMaximumSize(roomDto.getMaximumSize());
		room.setHostel(hostel);
		room.setRoomId(101);
		when(hostelDao.findById(201)).thenReturn(Optional.of(hostel));
		when(roomDao.save(any(Room.class))).thenReturn(room);
		when(roomDao.findAll()).thenReturn(Stream.of(room).collect(Collectors.toList()));

	}

	@Test
	@DisplayName("Test case to add room")
	public void testAddRoom() throws HostelNotFoundException {
//		when(hostelDao.findById(roomDto.getHostel_id())).thenReturn(Optional.of(hostel));
//		when(roomDao.save(any(Room.class))).thenReturn(room);
		assertEquals(room, roomService.addRoom(roomDto));

	}

	@Test
	@DisplayName("Negative test case to add room")
	public void testAddRoomNegative() {
//		when(hostelDao.findById(201)).thenReturn(Optional.of(hostel));
		roomDto.setHostel_id(202);
		Assertions.assertThrows(HostelNotFoundException.class, () -> roomService.addRoom(roomDto));
	}

//	@Test
//	@DisplayName("Test case to get room by hostel id")
//	public void testGetRoomsByHostelId() {
//		when(hostelDao.findById(roomDto.getHostel_id())).thenReturn(Optional.of(hostel));
//		when()
//		
//	}

	@Test
	@DisplayName("Test case to get room by wrong hostel id")
	public void testGetRoomsByHostelIdNegative() {

//		when(hostelDao.findById((int) 201)).thenReturn(Optional.of(hostel));
		Assertions.assertThrows(HostelNotFoundException.class, () -> roomService.getRoomsByHostelId((202)));
	}

	@Test
	@DisplayName("Test case to get rooms available")
	public void testGetRoomsAvailable() throws RoomNotFoundException {
//		when(roomDao.findAll()).thenReturn(Stream.of(room).collect(Collectors.toList()));
		assertEquals(1, roomService.getRoomsAvailable().size());
	}

}
