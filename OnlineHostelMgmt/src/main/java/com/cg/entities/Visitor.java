package com.cg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="visitors")
public class Visitor {
	
	
	


	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer Id;
	
	@Column(name="visitor_name", length=25)
	private String visitorname;
	
	@Column(name="contact_number", length=25)
	private String contactnumber;
	
	@Column(name="student_id", length=25)
	private int studentid;
	
	@Column(name="student_relation", length=25)
	private String studentrelation;
	
	@Column(name="student_address", length=25)
	private String studentaddress;
	
	@Column(name="reason", length=25)
	private String reason;
	
	public Visitor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Visitor(Integer id, String visitorname, String contactnumber, int studentid, String studentrelation,
			String studentaddress, String reason) {
		super();
		Id = id;
		this.visitorname = visitorname;
		this.contactnumber = contactnumber;
		this.studentid = studentid;
		this.studentrelation = studentrelation;
		this.studentaddress = studentaddress;
		this.reason = reason;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		Id = id;
	}

	/**
	 * @return the visitorname
	 */
	public String getVisitorname() {
		return visitorname;
	}

	/**
	 * @param visitorname the visitorname to set
	 */
	public void setVisitorname(String visitorname) {
		this.visitorname = visitorname;
	}

	/**
	 * @return the contactnumber
	 */
	public String getContactnumber() {
		return contactnumber;
	}

	/**
	 * @param contactnumber the contactnumber to set
	 */
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	/**
	 * @return the studentid
	 */
	public int getStudentid() {
		return studentid;
	}

	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	/**
	 * @return the studentrelation
	 */
	public String getStudentrelation() {
		return studentrelation;
	}

	/**
	 * @param studentrelation the studentrelation to set
	 */
	public void setStudentrelation(String studentrelation) {
		this.studentrelation = studentrelation;
	}

	/**
	 * @return the studentaddress
	 */
	public String getStudentaddress() {
		return studentaddress;
	}

	/**
	 * @param studentaddress the studentaddress to set
	 */
	public void setStudentaddress(String studentaddress) {
		this.studentaddress = studentaddress;
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
	

	@Override
	public String toString() {
		return "Visitors [Id=" + Id + ", visitorname=" + visitorname + ", contactnumber=" + contactnumber
				+ ", studentid=" + studentid + ", studentrelation=" + studentrelation + ", studentaddress="
				+ studentaddress + ", reason=" + reason + "]";
	}
}