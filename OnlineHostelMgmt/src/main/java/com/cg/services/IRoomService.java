package com.cg.services;

import java.util.List;

import com.cg.dto.RoomDTO;
import com.cg.entities.Room;
import com.cg.exceptions.FloorNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;

public interface IRoomService {
	
	public Room addRoom(RoomDTO roomdto) throws HostelNotFoundException;
	
	public List<Room> getRoomsByHostelId(Integer hostel_id)throws HostelNotFoundException,RoomNotFoundException;
	
	public List<Room> getRoomsByFloorAndHostelId(Integer floor,Integer hostel_id) throws HostelNotFoundException,FloorNotFoundException,RoomNotFoundException ;
	
	public List<Room> getRoomsAvailableByHostelId(Integer hostel_id)throws HostelNotFoundException,RoomNotFoundException;
	
	public List<Room> getRoomsAvailable() throws RoomNotFoundException;
	
	
		
	

}
