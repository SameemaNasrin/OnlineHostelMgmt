package com.cg.testRoom;

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
import com.cg.exceptions.FloorNotFoundException;
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

	Hostel hostel, hostel1;

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
		
		hostel1 = new Hostel();
		hostel1.setName(hostelDto.getName());
		hostel1.setContact(hostelDto.getContact());
		hostel1.setType(hostelDto.getType());
		hostel1.setAddress(hostelDto.getAddress());
		hostel1.setFee(hostelDto.getFee());
		hostel1.setId((204));

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
		assertEquals(room, roomService.addRoom(roomDto));

	}

	@Test
	@DisplayName("Negative test case to add room")
	public void testAddRoomNegative() {
		roomDto.setHostel_id(202);
		Assertions.assertThrows(HostelNotFoundException.class, () -> roomService.addRoom(roomDto));
	}

	@Test
	@DisplayName("Test case to get rooms by hostel id")
	public void testGetRoomsByHostelId() throws HostelNotFoundException, RoomNotFoundException {
		when(roomDao.findByHostelId(201)).thenReturn(Stream.of(room).collect(Collectors.toList()));
		assertEquals(1, roomService.getRoomsByHostelId(201).size());
	}

	@Test
	@DisplayName("Test case to get room by invalid hostel id")
	public void testGetRoomsByHostelIdNegative() {
		Assertions.assertThrows(HostelNotFoundException.class, () -> roomService.getRoomsByHostelId((202)));
	}
	
	@Test
	@DisplayName("Test case to find no rooms with valid hostel ID")
	public void testGetNoRoomsByHostelId() {
		when(hostelDao.findById(204)).thenReturn(Optional.of(hostel1));
		Assertions.assertThrows(RoomNotFoundException.class, ()->roomService.getRoomsByHostelId(204));
	}

	
	@Test
	@DisplayName("Test case for rooms unavailable with valid hostel id")
	public void testGetRoomsUnavailableByHostelId() {
		when(hostelDao.findById(204)).thenReturn(Optional.of(hostel1));
		Assertions.assertThrows(RoomNotFoundException.class, ()->roomService.getRoomsAvailableByHostelId(204));
	}
	
	@Test
	@DisplayName("Test case to get rooms by floor no. and hostel ID")
	public void testGetRoomsByFloorAndHostelId() throws HostelNotFoundException, FloorNotFoundException, RoomNotFoundException {
		when(roomDao.findByHostelIdAndFloor(201, 2)).thenReturn(Stream.of(room).collect(Collectors.toList()));
		assertEquals(1, roomService.getRoomsByFloorAndHostelId(2, 201).size());
	}
	
	@Test
	@DisplayName("Test case to get rooms by invalid floor no. and valid hostel ID")
	public void testGetRoomsByInvalidFloorAndValidHostelId() {
		when(roomDao.findByHostelIdAndFloor(201, 2)).thenReturn(Stream.of(room).collect(Collectors.toList()));
		Assertions.assertThrows(FloorNotFoundException.class, ()->roomService.getRoomsByFloorAndHostelId(6, 201));
	}
	
	@Test
	@DisplayName("Test case to get rooms by valid floor no. and invalid hostel ID")
	public void testGetRoomsByValidFloorAndInvalidHostelId() {
		when(roomDao.findByHostelIdAndFloor(201, 2)).thenReturn(Stream.of(room).collect(Collectors.toList()));
		Assertions.assertThrows(HostelNotFoundException.class, ()->roomService.getRoomsByFloorAndHostelId(2, 204));
	}
	
	@Test
	@DisplayName("Test case to get unavailable rooms by valid floor no. and hostel ID")
	public void testGetUnavailableRoomsByFloorAndHostelID() {
		when(roomDao.findByHostelIdAndFloor(201, 2)).thenReturn(Stream.of(room).collect(Collectors.toList()));
		Assertions.assertThrows(RoomNotFoundException.class, ()->roomService.getRoomsByFloorAndHostelId(3, 201));
	}
	
	@Test
	@DisplayName("Test case to get rooms available")
	public void testGetRoomsAvailable() throws RoomNotFoundException {
		assertEquals(1, roomService.getRoomsAvailable().size());
	}

}