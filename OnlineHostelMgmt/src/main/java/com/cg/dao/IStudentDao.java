package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Student;

public interface IStudentDao extends JpaRepository<Student, Integer> {

}
