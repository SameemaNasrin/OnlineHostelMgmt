package com.cg.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VisitorDTO {

	@NotBlank(message = "Visitor name cannot be empty")
	private String name;
	@NotBlank(message = "contact number cannot be empty")
	@Pattern(regexp = "^[0-9]{10}", message = "Mobile number should be 10 digits")
	private String number;
	@NotNull(message = "Student ID cannot be empty")
	private Integer studentId;
	@NotBlank(message = "student relation cannot be empty")
	private String studentRelation;
	@NotBlank(message = "visitor address cannot be empty")
	private String visitorAddress;
	@NotBlank(message = "reason name cannot be empty")
	private String reason;
	@NotNull(message = "Date of visiting should not be empty")
	@FutureOrPresent(message = "Must be a present or future date")
	private LocalDate dateOfVisiting;
	@NotNull(message = "hostel_id name cannot be empty")
	private Integer hostelId;

	public VisitorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VisitorDTO(String name, String number, Integer studentId, String studentRelation, String visitorAddress,
			String reason, LocalDate dateOfVisiting, Integer hostelId) {
		super();
		this.name = name;
		this.number = number;
		this.studentId = studentId;
		this.studentRelation = studentRelation;
		this.visitorAddress = visitorAddress;
		this.reason = reason;
		this.dateOfVisiting = dateOfVisiting;
		this.hostelId = hostelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentRelation() {
		return studentRelation;
	}

	public void setStudentRelation(String studentRelation) {
		this.studentRelation = studentRelation;
	}

	public String getVisitorAddress() {
		return visitorAddress;
	}

	public void setVisitorAddress(String visitorAddress) {
		this.visitorAddress = visitorAddress;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDate getDateOfVisiting() {
		return dateOfVisiting;
	}

	public void setDateOfVisiting(LocalDate dateOfVisiting) {
		this.dateOfVisiting = dateOfVisiting;
	}

	public Integer getHostelId() {
		return hostelId;
	}

	public void setHostelId(Integer hostelId) {
		this.hostelId = hostelId;
	}

}