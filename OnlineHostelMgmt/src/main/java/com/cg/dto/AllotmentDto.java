package com.cg.dto;

import javax.validation.constraints.NotBlank;

import com.cg.entities.Room;
import com.cg.entities.Student;

public class AllotmentDto {
	
	@NotBlank(message = "Allotment ID must not be blank")
	private Integer id;
	private Room room;
	private Student student;
	public AllotmentDto(@NotBlank(message = "Allotment ID must not be blank") Integer id, Room room, Student student) {
		super();
		this.id = id;
		this.room = room;
		this.student = student;
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
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
