package com.cg.entities;

import java.util.Set;
import javax.persistence.*;

import com.cg.helper.Helper;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hostel")
public class Hostel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hostel_id")
	private Integer id;

	@Column(name = "hostel_name", length = 100)
	private String name;

	@Column(name = "hostel_contact", length = 10)
	private String contact;

	@Column(name = "hostel_type", length = 10)
	private String type;

	@Column(name = "hostel_address", length = 140)
	private String address;

	@Column(name = "hostel_fee")
	private Double fee;

	@OneToMany(mappedBy = "hostel")
	@JsonIgnore
	private Set<Room> rooms;
	
	@Column(name="total_floors")
	private final Integer totalFloors= Helper.FIVE;
	
	@Column(name="img_url")
	private String imgUrl;

	public Hostel() {
		super();
	}

	public Hostel(Integer id, String name, String contact, String type, String address, Double fee, Set<Room> rooms) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.type = type;
		this.address = address;
		this.fee = fee;
		this.rooms = rooms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getTotalFloors() {
		return totalFloors;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
}
