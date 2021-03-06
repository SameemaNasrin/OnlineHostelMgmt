package com.cg.dto;

import java.time.LocalDate;

import javax.validation.constraints.*;

import com.cg.helper.Helper;

public class FeeStructureDto {

	private Integer id;
	@NotNull(message = "Student ID must not be blank")
	private Integer studentId;
	@NotNull(message = "Fees cannot be null")
	private Double totalFees;
	private Integer allotmentId;
	private String paymentStatus = Helper.NOT_PAID;
	private LocalDate paymentDate;
	
	//constructor
	public FeeStructureDto() {
		super();
	}
	
	
	//getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	public Integer getAllotmentId() {
		return allotmentId;
	}

	public void setAllotmentId(Integer allotmentId) {
		this.allotmentId = allotmentId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	
	
	
}
