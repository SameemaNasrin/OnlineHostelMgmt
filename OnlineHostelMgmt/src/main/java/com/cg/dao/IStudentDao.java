package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.entities.Student;

public interface IStudentDao extends JpaRepository<Student, Integer> {
	public List<Student> findByNameContaining(String name);
	public Student findByMobile(String mobile);
}
