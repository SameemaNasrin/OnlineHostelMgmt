package com.cg.dto;

import javax.validation.constraints.*;

public class RoomDTO {

	private Integer roomId;

	@NotBlank(message = "Room number cannot be empty")
	private String roomNo;

	@NotBlank(message = "Room desc cannot be empty")
	private String roomDesc;

	@Min(value = 0, message = "Floor number cannot be less than 0")
	@Max(value = 5, message = "Floor number cannot be more than 5")
	@NotNull(message = "Floor cannot be empty")
	private Integer floor;

	@Max(value = 4, message = "Max size cannot be more than 4")
	private Integer maximumSize = 4;

	@Min(value = 1, message = "Hostel id cannot be less than 1")
	@NotNull(message = "Hostel id cannot be null")
	private Integer hostelId;

	public RoomDTO() {
		super();
	}

	public RoomDTO(Integer roomId, String roomNo, String roomDesc, Integer floor, Integer maximumSize, Integer hostel_id) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomDesc = roomDesc;
		this.floor = floor;
		this.maximumSize = maximumSize;
		this.hostelId = hostel_id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(Integer maximumSize) {
		this.maximumSize = maximumSize;
	}

	public Integer getHostel_id() {
		return hostelId;
	}

	public void setHostel_id(Integer hostel_id) {
		this.hostelId = hostel_id;
	}

}
