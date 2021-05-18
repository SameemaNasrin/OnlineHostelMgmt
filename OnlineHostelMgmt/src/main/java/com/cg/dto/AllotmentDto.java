package com.cg.dto;

public class AllotmentDto {
	
	private Integer id;
	private Integer roomId;
	private Integer studentId;

	//Constructors for AllotmentDto
	public AllotmentDto(Integer id, Integer roomId, Integer studentId) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.studentId = studentId;
	}
	//getters and setters
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
