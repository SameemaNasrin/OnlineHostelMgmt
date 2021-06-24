package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Student;

@Repository
public interface IStudentDao extends JpaRepository<Student, Integer> {
	public List<Student> findByNameContaining(String name);

	public List<Student> findByMobile(String mobile);
	public List<Student> findByEmail(String email);
}
