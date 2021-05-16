package com.cg.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.cg.entities.Hostel;

public class RoomDTO {

	private Integer roomId;

	@NotBlank(message = "Room number cannot be empty")
	private String roomNo;

	@NotBlank(message = "Room desc cannot be empty")
	private String roomDesc;

	@Min(value = 0, message = "Floor number cannot be less than 0")
	private Integer floor;

	@Max(value = 4, message = "Max size cannot be more than 4")
	private Integer maximumSize = 4;

	@Min(value = 1, message = "Hostel id cannot be less than 1")
	private Long hostel_id;

	public RoomDTO() {
		super();
		// TODO Auto-generated constructor stub

	}

	public RoomDTO(Integer roomId, String roomNo, String roomDesc, Integer floor, Integer maximumSize, Long hostel_id) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomDesc = roomDesc;
		this.floor = floor;
		this.maximumSize = maximumSize;
		this.hostel_id = hostel_id;
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

	public Long getHostel_id() {
		return hostel_id;
	}

	public void setHostel_id(Long hostel_id) {
		this.hostel_id = hostel_id;
	}

}
