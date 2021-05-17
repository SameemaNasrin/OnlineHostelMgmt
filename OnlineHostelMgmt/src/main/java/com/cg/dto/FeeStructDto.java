package com.cg.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cg.entities.Allotment;
import com.cg.entities.Student;

public class FeeStructDto {

	private Integer id;
	@NotBlank(message = "Student ID must not be blank")
	private Student student;
	@NotBlank(message = "Fees cannot be null")
	private Double totalFees;
	@NotBlank(message = "Allotment ID must not be blank")
	private Allotment allotment;
	private String paymentStatus = "In Progress";
	private LocalDate paymentDate;
	public FeeStructDto() {
		super();
	}
	public FeeStructDto(Integer id, @NotNull(message = "Student ID must not be blank") Student student,
			@NotBlank(message = "Fees cannot be null") Double totalFees,
			@NotBlank(message = "Allotment ID must not be blank") Allotment allotment, String paymentStatus,
			LocalDate paymentDate) {
		super();
		this.id = id;
		this.student = student;
		this.totalFees = totalFees;
		this.allotment = allotment;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Double getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}
	public Allotment getAllotment() {
		return allotment;
	}
	public void setAllotment(Allotment allotment) {
		this.allotment = allotment;
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
