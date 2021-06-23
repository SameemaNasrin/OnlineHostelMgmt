package com.cg.services;

import java.util.List;

import com.cg.dto.StudentDTO;
import com.cg.entities.Student;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.StudentNotFoundException;

public interface IStudentService {
	public Student addStudent(StudentDTO studentDto) throws EmailAlreadyExistException;
	public List<Student> getStudents() throws StudentNotFoundException;
	public Integer removeStudentById(Integer studentId) throws StudentNotFoundException;
	public Student getStudentById(Integer studentId) throws StudentNotFoundException;
	public List<Student> getStudentByName(String name) throws StudentNotFoundException;
	public Student getStudentByMobileNumber(String mobileNumber) throws StudentNotFoundException;
}
