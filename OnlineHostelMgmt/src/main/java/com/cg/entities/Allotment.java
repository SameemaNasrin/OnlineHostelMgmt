package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "allotments")
public class Allotment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "allotment_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "room_id")
	private Room room;

	@OneToOne
	@JoinColumn(name = "student_id", referencedColumnName = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "hostel_id", referencedColumnName = "hostel_id")
	private Hostel hostel;

	// constructors
	public Allotment(Integer id, Room room, Student student, Hostel hostel) {
		super();
		this.id = id;
		this.room = room;
		this.student = student;
		this.hostel = hostel;
	}

	public Allotment() {
		super();
	}

	// getter and setters

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
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