package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="allotments")
public class Allotment {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private Integer hostelId;
	private Integer roomId;
	private Integer studentId;
	public Allotment() {
		super();
	}
	public Allotment(Integer id, Integer hostelId, Integer roomId, Integer studentId) {
		super();
		Id = id;
		this.hostelId = hostelId;
		this.roomId = roomId;
		this.studentId = studentId;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getHostelId() {
		return hostelId;
	}
	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
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
	@Override
	public String toString() {
		return  Id + " " + hostelId + " " + roomId + " " + studentId;
	}
	
}
