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

	//primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="allotment_id")
	private Integer id;
	
	//relationship with room entity table
	@ManyToOne
	@JoinColumn(name="room_id",referencedColumnName="room_id")
	private Room room;
	//relationship with student entity table	
	@ManyToOne
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	private Student student;

	//constructors
	public Allotment(Integer id, Room room, Student student) {
		super();
		this.id = id;
		this.room = room;
		this.student = student;
	}

	public Allotment() {
		super();
	}
	
	//getter and setters
	

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