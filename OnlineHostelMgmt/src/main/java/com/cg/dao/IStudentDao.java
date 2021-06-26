package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.Student;

@Repository
public interface IStudentDao extends JpaRepository<Student, Integer> {
	public List<Student> findByNameContaining(String name);

	public List<Student> findByMobile(String mobile);
	public List<Student> findByEmail(String email);
	
	@Query("SELECT s FROM Student s WHERE s.allotment is null")
	public List<Student> findUnallottedStudents();
	@Query("SELECT s FROM Student s WHERE s.allotment is not null")
	public List<Student> findAllottedStudents();
}
