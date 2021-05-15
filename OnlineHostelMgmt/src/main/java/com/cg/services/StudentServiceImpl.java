package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IStudentDao;
import com.cg.dto.StudentDTO;
import com.cg.entities.Student;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	IStudentDao studentDao;

	@Override
	public Student addStudent(StudentDTO studentDto) {
		Student student = new Student();
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setGender(studentDto.getGender());
		student.setAddress(studentDto.getAddress());
		student.setDob(studentDto.getDob());
		student.setGuardianName(studentDto.getGuardianName());
		student.setMobile(studentDto.getMobile());
		return studentDao.save(student);
	}

	@Override
	public Boolean removeStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudents() {
		return studentDao.findAll();
	}

}
