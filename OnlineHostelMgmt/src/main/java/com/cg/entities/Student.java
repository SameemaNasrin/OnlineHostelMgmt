package com.cg.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Integer id;

	@Column(name = "student_name")
	private String name;

	@Column(name = "student_email")
	private String email;

	@Column(name = "student_gender")
	private String gender;

	@Column(name = "student_dob")
	private LocalDate dob;

	@Column(name = "student_mobile_no")
	private String mobile;

	@Column(name = "student_address")
	private String address;

	@Column(name = "guardian_name")
	private String guardianName;
	
	@OneToOne
	@JoinColumn(name = "allotment_id", referencedColumnName = "allotment_id")
	@JsonIgnore
	private Allotment allotment;

	public Student() {
		super();
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

	public Allotment getAllotment() {
		return allotment;
	}

	public void setAllotment(Allotment allotment) {
		this.allotment = allotment;
	}

	

}
