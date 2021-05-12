package com.cg.entities;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hostel")
public class Hostel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;

	private String name;
	private String contact;
	private String type;
	private String address;
	private Double fee;

	private Warden warden;

	private Set<Room> rooms;

	public Hostel() {
		super();

	}

	public Hostel(Integer id, String name, String contact, String type, String address, Double fee, Warden warden,
			Set<Room> rooms) {
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
	// Getters and Setters

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Warden getWarden() {
		return this.warden;
	}

	public void setWarden(Warden warden) {
		this.warden = warden;
	}

	public Set<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + contact + " " + type + " " + address + " " + fee;
	}

}
