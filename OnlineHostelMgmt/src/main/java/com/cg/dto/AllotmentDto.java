package com.cg.dto;

import javax.validation.constraints.NotNull;

public class AllotmentDto {

	private Integer id;
	@NotNull(message = "Room id cannot be null")
	private Integer roomId;
	@NotNull(message = "Student id cannot be null")
	private Integer studentId;
	@NotNull(message = "Hostel id cannot be null")
	private Integer hostelId;
	// Constructors for AllotmentDto
	private Double totalFees;

	public AllotmentDto(Integer id, Integer roomId, Integer studentId, Integer hostelId,Double totalFees) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.hostelId=hostelId;
		this.studentId = studentId;
		this.totalFees=totalFees;
		
	}

	public Integer getHostelId() {
		return hostelId;
	}

	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	// getters and setters
	public AllotmentDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


}
