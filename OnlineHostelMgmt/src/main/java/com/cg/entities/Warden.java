package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "warden")
public class Warden {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="warden_id")
	private Integer id;
	
	@Column(name="warden_email", length = 30)
	private String email;
	
	@Column(name="warden_name", length = 25)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="hostel_id",referencedColumnName = "hostel_id")
	@JsonIgnore
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
