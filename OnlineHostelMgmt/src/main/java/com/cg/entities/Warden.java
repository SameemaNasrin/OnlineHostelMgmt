package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="wardens")
public class Warden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String email;
	private String name;
	private Integer hostelId;
	private Hostel hostel;
	
	public Warden() {
		super();
	}
	
	public Warden(Integer id, String email, String name, Integer hostelId) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.hostelId = hostelId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHostelId() {
		return hostelId;
	}

	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
	}

	@Override
	public String toString() {
		return  id + " " + email + " " + name + " " + hostelId;
	}
	
}
