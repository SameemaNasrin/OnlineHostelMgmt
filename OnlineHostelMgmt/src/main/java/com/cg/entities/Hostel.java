package com.cg.entities;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "hostel")
public class Hostel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hostel_id")
	private Long id;
	
	@Column(name="hostel_name",length = 25)
	private String name;

	@Column(name="hostel_contact",length = 10)
	private String contact;

	@Column(name="hostel_type",length = 10)
	private String type;
	
	@Column(name="hostel_address",length = 40)
	private String address;
	
	@Column(name="hostel_fee")
	private Double fee;

	@OneToMany(mappedBy = "hostel")
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

	public Hostel(Long id, String name, String contact, String type, String address, Double fee, Set<Room> rooms) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.type = type;
		this.address = address;
		this.fee = fee;
		this.rooms = rooms;
	}
	
	
	@Override
	public String toString() {
		return id + " " + name + " " + contact + " " + type + " " + address + " " + fee;
	}
	
}
