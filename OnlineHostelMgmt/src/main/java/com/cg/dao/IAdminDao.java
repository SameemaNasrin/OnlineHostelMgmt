package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Admin;
import com.cg.entities.Student;

public interface IAdminDao extends JpaRepository<Admin, Integer> {
	public List<Admin> findByEmail(String email);
}
