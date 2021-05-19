package com.cg.dto;

import java.time.LocalDate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.UniqueElements;

public class StudentDTO {
	@NotBlank(message = "Student name cannot be empty")
	private String name;
	@NotBlank(message = "Student email cannot be empty")
	@Email(message = "Must be a valid email id")
	private String email;
	@NotBlank(message = "Student gender cannot be empty")
	@Pattern(regexp = "(male|female|other)", message = "Gender should be male/female/other")
	private String gender;
	@Past(message = "Student date of birth must be a previous date")
	private LocalDate dob;
	@Pattern(regexp = "^[0-9]{10}",message = "Mobile number should be 10 digits")
	private String mobile;
	private String address;
	private String guardianName;

	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDTO(String name, String email, String gender, LocalDate dob, String mobile,
			String address, String guardianName) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.mobile = mobile;
		this.address = address;
		this.guardianName = guardianName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

}
