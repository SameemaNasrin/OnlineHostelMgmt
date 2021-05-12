package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "visitors")
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;

	@Column(name = "visitor_name", length = 25)
	private String visitorName;

	@Column(name = "contact_number", length = 25)
	private String contactNumber;

	private Student student;

	@Column(name = "student_relation", length = 25)
	private String studentRelation;

	@Column(name = "student_address", length = 25)
	private String visitorAddress;

	private String reason;

	public Visitor() {
		super();
	}

	public Visitor(Integer id, String visitorName, String contactNumber, Student student, String studentRelation,
			String visitorAddress, String reason) {
		super();
		this.id = id;
		this.visitorName = visitorName;
		this.contactNumber = contactNumber;
		this.student = student;
		this.studentRelation = studentRelation;
		this.visitorAddress = visitorAddress;
		this.reason = reason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", visitorName=" + visitorName + ", contactNumber=" + contactNumber + ", student="
				+ student + ", studentRelation=" + studentRelation + ", visitorAddress=" + visitorAddress + ", reason="
				+ reason + "]";
	}

}