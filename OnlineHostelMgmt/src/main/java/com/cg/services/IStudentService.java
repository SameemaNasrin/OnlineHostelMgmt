package com.cg.services;

import java.util.List;

import com.cg.dto.StudentDTO;
import com.cg.entities.Student;

public interface IStudentService {
	public Student addStudent(StudentDTO studentDto);
	public List<Student> getStudents();
	public Boolean removeStudentById(Integer studentId);
	public Student getStudentById(Integer studentId);
	public List<Student> getStudentByName(String name);
	public Student getStudentByMobileNumber(String mobileNumber);
}
