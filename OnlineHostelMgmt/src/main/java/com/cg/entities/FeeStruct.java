package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feestructures")
public class FeeStruct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private Student student;
	private Double TotalFees;
	private Integer Allotment;
	
	public FeeStruct() {
		super();
	}

	public FeeStruct(Integer id, Student student, Double totalFees, Integer allotment) {
		super();
		Id = id;
		this.student = student;
		TotalFees = totalFees;
		Allotment = allotment;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Double getTotalFees() {
		return TotalFees;
	}

	public void setTotalFees(Double totalFees) {
		TotalFees = totalFees;
	}

	public Integer getAllotment() {
		return Allotment;
	}

	public void setAllotment(Integer allotment) {
		Allotment = allotment;
	}

	@Override
	public String toString() {
		return  Id + " " + student + " " + TotalFees + " " + Allotment;
	}

	
}
