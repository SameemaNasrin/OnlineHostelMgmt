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
	private Integer studentId;
	private Double TotalFees;
	private Integer Allotment;
	
	public FeeStruct() {
		super();
	}

	public FeeStruct(Integer id, Integer studentId, Double totalFees, Integer allotment) {
		super();
		Id = id;
		this.studentId = studentId;
		TotalFees = totalFees;
		Allotment = allotment;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public double getTotalFees() {
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
		return Id + " " + studentId + " " + TotalFees + " "+ Allotment;
	}

}
