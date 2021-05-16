package com.cg.dto;

import javax.validation.constraints.NotBlank;

import com.cg.entities.Hostel;

public class WardenDto {
	
	@NotBlank(message = "Warden Id must not be blank")
	private Integer id;
	@NotBlank(message = "Email must not be blank")
	private String email;
	@NotBlank(message = "Name must not be blank")
	private String name;
	private Hostel hostel;
	
	public WardenDto() {
		super();
	}

	public WardenDto(Integer id, String email, String name, Hostel hostel) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.hostel = hostel;
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

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

}
