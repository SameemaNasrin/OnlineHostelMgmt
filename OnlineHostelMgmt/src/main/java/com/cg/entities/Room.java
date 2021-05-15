package com.cg.entities;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private Integer roomId;

	@Column(name = "room_no", length = 25)
	private String roomNo;
	@ManyToOne
	@JoinColumn(name = "hostel_id", referencedColumnName = "hostel_id")
	private Hostel hostel;

	@Column(name = "room_desc", length = 40)
	private String roomDesc;
	@Column(name = "floor")
	private Integer floor;
	@Column(name = "maximum_size")
	private Integer maximumSize = 4;

	// constructors
	public Room() {
		super();
	}

	public Room(Integer roomId, String roomNo, Hostel hostel, String roomDesc, Integer floor, Integer maximumSize) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.hostel = hostel;
		this.roomDesc = roomDesc;
		this.floor = floor;
		this.maximumSize = maximumSize;
	}

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

	public Integer getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(Integer maximumSize) {
		this.maximumSize = maximumSize;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNo=" + roomNo + ", roomDesc=" + roomDesc + ", floor=" + floor
				+ ", maximumSize=" + maximumSize + "]";
	}

}
