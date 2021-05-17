package com.cg.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fee_structures")
public class FeeStruct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fee_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "student_id", referencedColumnName = "student_id")
	private Integer studentId;

	@Column(name = "total_fees")
	private Double totalFees;

	@ManyToOne
	@JoinColumn(name = "allotment_id", referencedColumnName = "allotment_id")
	private Integer allotmentId;

	@Column(name = "payment_status")
	private String paymentStatus = "In Progress";

	@Column(name = "payment_date")
	private LocalDate paymentDate;

	public FeeStruct() {
		super();
	}

	public FeeStruct(Integer id, Integer studentId, Double totalFees, Integer allotmentId, String paymentStatus,
			LocalDate paymentDate) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.totalFees = totalFees;
		this.allotmentId = allotmentId;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
	}

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

	@Override
	public String toString() {
		return id + " " + studentId + " " + totalFees + " "
				+ allotmentId + " " + paymentStatus + " " + paymentDate;
	}

	
	

}
