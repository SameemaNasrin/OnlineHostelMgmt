package com.cg.dto;

import javax.validation.constraints.*;

public class HostelDto {

	private Integer id;

	@NotBlank(message = "Hostel name must not be blank")
	private String name;

	@NotBlank(message = "Contact number must not be blank")
	@Pattern(regexp = "^[0-9]{10}",message = "Mobile number should be 10 digits")
	private String contact;

	@NotBlank(message = "type cannot be blank")
	@Pattern(regexp = "(boys|girls|others)", message = "Type must be boys, girls or others")
	private String type;

	@NotBlank(message = "Address must not be blank")
	private String address;

	@Min(value = 0, message = "Fee cannot be negative")
	private Double fee;//we have fee here
	
	@Max(value=5,message="Total floors cannot be more than 5")
	private final Integer totalFloors=5;

	public HostelDto() {

	}

	public HostelDto(Integer id, String name, String contact, String type, String address, Double fee) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.type = type;
		this.address = address;
		this.fee = fee;
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

	public Integer getTotalFloors() {
		return totalFloors;
	}
	

}
