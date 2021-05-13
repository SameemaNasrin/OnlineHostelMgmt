package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allotments")
public class Allotment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Room room;
	private Student student;
	private Hostel hostel;

	public Allotment() {
		super();
	}
	
	public Allotment(Integer id, Room room, Student student, Hostel hostel) {
		super();
		this.id = id;
		this.room = room;
		this.student = student;
		this.hostel = hostel;
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
	

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	@Override
	public String toString() {
		return "Allotment [Id=" + id + ", room=" + room + ", student=" + student + "]";
	}

}