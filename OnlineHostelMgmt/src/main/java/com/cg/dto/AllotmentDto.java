package com.cg.dto;

import javax.validation.constraints.NotNull;

public class AllotmentDto {
	
	@NotNull(message = "Allotment ID must not be blank")
	private Integer id;
	private Integer roomId;
	private Integer studentId;

	
	public AllotmentDto(@NotNull(message = "Allotment ID must not be blank") Integer id, Integer roomId,
			Integer studentId) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.studentId = studentId;
	}
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
