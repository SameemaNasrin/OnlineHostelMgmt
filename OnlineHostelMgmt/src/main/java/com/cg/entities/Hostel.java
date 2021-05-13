package com.cg.entities;

import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hostel")
public class Hostel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hostel_id")
	private Long id;
	
	@Column(length = 25)
	private String name;

	@Column(length = 10)
	private String contact;

	@Column(length = 10)
	private String type;
	
	@Column(length = 40)
	private String address;
	
	private Double fee;
	
	private Warden warden;
	

	private Set<Room> rooms;
	
	
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Warden getWarden() {
		return warden;
	}

	public void setWarden(Warden warden) {
		this.warden = warden;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	
	//Constructors
	public Hostel() {
		super();
	
	}

	public Hostel(Long id, String name, String contact, String type, String address, Double fee, Warden warden, Set<Room> rooms) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.type = type;
		this.address = address;
		this.fee = fee;
		this.warden = warden;
		this.rooms = rooms;
	}
	
	
	@Override
	public String toString() {
		return id + " " + name + " " + contact + " " + type + " " + address + " " + fee;
	}
	
}
