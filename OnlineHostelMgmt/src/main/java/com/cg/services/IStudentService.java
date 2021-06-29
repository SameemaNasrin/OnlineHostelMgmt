package com.cg.services;

import java.util.List;
import java.util.Map;

import com.cg.dto.StudentDTO;
import com.cg.entities.Allotment;
import com.cg.entities.FeeStructure;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.MobileNumberAlreadyExistsException;
import com.cg.exceptions.StudentNotFoundException;

public interface IStudentService {
	public Map<String, String> addStudent(StudentDTO studentDto) throws EmailAlreadyExistException,MobileNumberAlreadyExistsException;
	public List<Student> getStudents() throws StudentNotFoundException;
	public Integer removeStudentById(Integer studentId) throws StudentNotFoundException;
	public Student getStudentById(Integer studentId) throws StudentNotFoundException;
	public List<Student> getStudentByName(String name) throws StudentNotFoundException;
	public Student getStudentByMobileNumber(String mobileNumber) throws StudentNotFoundException;
	public List<Student> getUnallottedStudents() throws StudentNotFoundException;
	public List<Student> getAllottedStudents() throws StudentNotFoundException;
	public FeeStructure getFeeStructureByStudentId(Integer student_id) throws StudentNotFoundException,AllotmentNotFoundException,FeeStructureNotFoundException;
}
