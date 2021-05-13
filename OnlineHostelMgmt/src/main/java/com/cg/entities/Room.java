package com.cg.entities;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="room_id")
	private Integer roomId;
	
	@Column(name="room_no", length=25)
	private String roomNo;
	
	private Hostel hostel;
	
	@Column(name="room_desc", length=40)
	private String roomDesc;
	
	private Integer floor;

	//constructors
	public Room() {
		super();
	}

	public Room(Integer roomId, String roomNo, Hostel hostel, String roomDesc, Integer floor) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.hostel = hostel;
		this.roomDesc = roomDesc;
		this.floor = floor;
	}




	//getters and setters
	public Integer getRoomId() {
		return roomId;
	}


	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public String getRoomNo() {
		return roomNo;
	}


	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public Hostel getHostel() {
		return hostel;
	}


	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}


	public String getRoomDesc() {
		return roomDesc;
	}


	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}


	public Integer getFloor() {
		return floor;
	}


	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString() {
		return roomId + " " + roomNo + " " + roomDesc + " " + floor;
	}

}
