package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.RoomDTO;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Room;

import com.cg.exceptions.FloorNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.ValidateRoomException;
import com.cg.services.IRoomService;

@RestController
@RequestMapping("/room")
public class RoomRestController {

	@Autowired
	IRoomService roomService;

	@PostMapping("/add")
	public SuccessMessage addRoom(@Valid @RequestBody RoomDTO roomDto, BindingResult br)
			throws ValidateRoomException, HostelNotFoundException {
		if (br.hasErrors()) {
			throw new ValidateRoomException(br.getFieldErrors());
		}
		Integer roomId = roomService.addRoom(roomDto).getRoomId();
		return new SuccessMessage("Room id: " + roomId + " added");

	}

	@GetMapping("/get/{hid}")
	public ResponseEntity<List<Room>> viewRoomsByHostelId(@PathVariable("hid") Long hostel_id)
			throws HostelNotFoundException, RoomNotFoundException {
		return new ResponseEntity<List<Room>>(roomService.getRoomsByHostelId(hostel_id), HttpStatus.OK);
	}

	@GetMapping("/get/{hid}/{flr}")
	public ResponseEntity<List<Room>> viewRoomsByFloorAndHostelId(@PathVariable("hid") Long hostel_id,
			@PathVariable("flr") Integer floor_id)
			throws HostelNotFoundException, RoomNotFoundException, FloorNotFoundException {
		return new ResponseEntity<List<Room>>(roomService.getRoomsByFloorAndHostelId(floor_id, hostel_id),
				HttpStatus.OK);
	}

	@GetMapping("/get/available/{hid}")
	public ResponseEntity<List<Room>> viewRoomsAvailableByHostelId(@PathVariable("hid") Long hostel_id)
			throws HostelNotFoundException, RoomNotFoundException {
		return new ResponseEntity<List<Room>>(roomService.getRoomsAvailableByHostelId(hostel_id), HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Room>> viewRoomsAvailable() throws HostelNotFoundException, RoomNotFoundException {
		return new ResponseEntity<List<Room>>(roomService.getRoomsAvailable(), HttpStatus.OK);
	}

}
