package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.RoomDTO;
import com.cg.dto.StudentDTO;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.services.IRoomService;

@RestController
@RequestMapping("/room")
public class RoomRestController {
	
	@Autowired
	IRoomService roomService;
	
	@PostMapping("/add")
	public ResponseEntity<Room> addRoom(@RequestBody RoomDTO roomDto) throws HostelNotFoundException {
		return new ResponseEntity<Room>(roomService.addRoom(roomDto), HttpStatus.CREATED);

	}

	@GetMapping("/get/{hid}")
	public ResponseEntity<List<Room>> viewRoomsByHostelId(@PathVariable("hid")Long hostel_id) throws HostelNotFoundException, RoomNotFoundException {
		return new ResponseEntity<List<Room>>(roomService.getRoomsByHostelId(hostel_id), HttpStatus.OK);
	}
	
	
	
	
	

}
