package com.cg.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AllotmentDto {

	private Integer id;
	@NotNull(message = "Room id cannot be null")
	private Integer roomId;
	@NotNull(message = "Student id cannot be null")
	private Integer studentId;
	@NotNull(message = "Total fees cannot be null")
	@Min(value = 0, message = "Total fees must be greater than 0")
	private Double totalFees; // to save the total fees in db
	// Constructors for AllotmentDto

	public AllotmentDto(Integer id, Integer roomId, Integer studentId, Double totalFees) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.studentId = studentId;
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

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

}
