package com.cg.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

public class VisitorDTO {

	@NotBlank(message = "Visitor name cannot be empty")
	private String name;
	@NotBlank(message = "contact number cannot be empty")
	private String number;
	@NotBlank(message = "Student ID cannot be empty")
	private Integer student_id;
	@NotBlank(message = "student relation cannot be empty")
	private String studentRelation;
	@NotBlank(message = "visitor address cannot be empty")
	private String visitorAddress;
	@NotBlank(message = "reason name cannot be empty")
	private String reason;
	@NotBlank(message = "date_of_visiting name cannot be empty")
	private LocalDate date_of_visiting;
	@NotBlank(message = "hostel_id name cannot be empty")
	private Long hostel_id;

	public VisitorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VisitorDTO(String name, String number, Integer student_id, String studentRelation, String visitorAddress,
			String reason, LocalDate date_of_visiting, Long hostel_id) {
		super();
		this.name = name;
		this.number = number;
		this.student_id = student_id;
		this.studentRelation = studentRelation;
		this.visitorAddress = visitorAddress;
		this.reason = reason;
		this.date_of_visiting = date_of_visiting;
		this.hostel_id = hostel_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the student_id
	 */
	public Integer getStudent_id() {
		return student_id;
	}

	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	/**
	 * @return the studentRelation
	 */
	public String getStudentRelation() {
		return studentRelation;
	}

	/**
	 * @param studentRelation the studentRelation to set
	 */
	public void setStudentRelation(String studentRelation) {
		this.studentRelation = studentRelation;
	}

	/**
	 * @return the visitorAddress
	 */
	public String getVisitorAddress() {
		return visitorAddress;
	}

	/**
	 * @param visitorAddress the visitorAddress to set
	 */
	public void setVisitorAddress(String visitorAddress) {
		this.visitorAddress = visitorAddress;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the date_of_visiting
	 */
	public LocalDate getDate_of_visiting() {
		return date_of_visiting;
	}

	/**
	 * @param date_of_visiting the date_of_visiting to set
	 */
	public void setDate_of_visiting(LocalDate date_of_visiting) {
		this.date_of_visiting = date_of_visiting;
	}

	/**
	 * @return the hostel_id
	 */
	public Long getHostel_id() {
		return hostel_id;
	}

	/**
	 * @param hostel_id the hostel_id to set
	 */
	public void setHostel_id(Long hostel_id) {
		this.hostel_id = hostel_id;
	}
}