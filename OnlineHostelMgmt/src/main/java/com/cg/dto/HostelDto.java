package com.cg.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class HostelDto {

	
	private Long id;
	
	@NotBlank(message = "Hostel name must not be blank")
	private String name;
	
	@NotBlank(message = "Contact number must not be blank")
	private String contact;
	
	@NotBlank(message = "type cannot be blank")
	@Pattern(regexp = "(boys|girls)", message = "Type must be boys or girls")
	private String type;
	
	@NotBlank(message = "Address must not be blank")
	private String address;
	
	@Min(value = 0, message = "Fee cannot be negative")
	private Double fee;
	
	
	
	public HostelDto() {
		
	}
	
	public HostelDto(Long id, String name, String contact, String type, String address, Double fee) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.type = type;
		this.address = address;
		this.fee = fee;
	}

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
	
	
	

}
