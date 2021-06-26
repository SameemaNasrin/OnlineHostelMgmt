package com.cg.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * @Author: Supriyo Das
 */
@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "role", length = 25)
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id")
	private Student student;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "warden_id", referencedColumnName = "warden_id")
	private Warden warden;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
	private Admin admin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Warden getWarden() {
		return warden;
	}

	public void setWarden(Warden warden) {
		this.warden = warden;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", student="
				+ student + ", warden=" + warden + ", admin=" + admin + "]";
	}
	
	
	

}
