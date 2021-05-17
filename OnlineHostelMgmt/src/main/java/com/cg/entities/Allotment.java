package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "allotments")
public class Allotment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="allotment_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="room_id",referencedColumnName="room_id")
	private Integer roomId;
	
	@ManyToOne
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	private Integer studentId;

	public Allotment() {
		super();
	}

	public Integer getId() {
		return this.id;
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

	public void setId(Integer id) {
		this.id = id;
	}


	
}