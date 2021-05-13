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
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	private Student student;

	public Allotment() {
		super();
	}

	public Integer getId() {
		return this.id;
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