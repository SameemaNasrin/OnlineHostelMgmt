package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wardens")
public class Warden {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Warden_id")
	private Integer id;
	
	@Column(name="warden_email")
	private String email;
	
	@Column(name="warden_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="hostel_id",referencedColumnName = "hostel_id")
	private Hostel hostel;
	
	//constructors
	public Warden() {
		super();
	}


	public Warden(Integer id, String email, String name, Hostel hostel) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.hostel = hostel;
	}

	//getters and setters
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

	

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	@Override
	public String toString() {
		return id + " " + email + " " + name;
	}

}
