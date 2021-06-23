package com.cg.services;
import java.util.ArrayList;
/*
 * @Author: Supriyo Das
 * @Created at: 20.05.2021
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IStudentDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.StudentDTO;
import com.cg.entities.Student;
import com.cg.entities.Warden;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.helper.Helper;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	IStudentDao studentDao;
	
	@Autowired
	IWardenDao wardenDao;

	@Override
	public Student addStudent(StudentDTO studentDto) throws EmailAlreadyExistException {
		Student student = new Student();
		student.setName(studentDto.getName());
		/*
		 * checking for unique email
		 * run a function-> return type- list of email
		 * if returned list is empty then add student
		 * else if return list is not empty then throw exception that the email is already registered
		 */
		List<Student> studentEmail = checkStudentEmail(studentDto.getEmail());
		List<Warden> wardenEmail = checkWardenEmail(studentDto.getEmail());

		if(studentEmail.size()>0 || wardenEmail.size()>0)
			throw new EmailAlreadyExistException("This Email is already registered");
		
		student.setEmail(studentDto.getEmail());
		student.setGender(studentDto.getGender());
		student.setAddress(studentDto.getAddress());
		student.setDob(studentDto.getDob());
		student.setGuardianName(studentDto.getGuardianName());
		student.setMobile(studentDto.getMobile());
		return studentDao.save(student);
	}

	@Override
	public List<Student> getStudents() throws StudentNotFoundException {
		List<Student> studentList = studentDao.findAll();
		if (studentList.isEmpty()) {
			throw new StudentNotFoundException(Helper.NO_STUDENT_FOUND);
		}

		return studentList;
	}

	@Override
	public Integer removeStudentById(Integer studentId) throws StudentNotFoundException {
		Student student = studentDao.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException(Helper.NO_STUDENT_FOUND_WITH_ID + studentId));
		studentDao.delete(student);
		return student.getId();
	}

	@Override
	public Student getStudentById(Integer studentId) throws StudentNotFoundException {
		return studentDao.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException(Helper.NO_STUDENT_FOUND_WITH_ID + studentId));
	}

	@Override
	public List<Student> getStudentByName(String name) throws StudentNotFoundException {
		List<Student> students = studentDao.findByNameContaining(name);
		if (students.isEmpty())
			throw new StudentNotFoundException(Helper.NO_STUDENT_FOUND_WITH_NAME + name);
		return students;
	}

	@Override
	public Student getStudentByMobileNumber(String mobileNumber) throws StudentNotFoundException {
		Student student = studentDao.findByMobile(mobileNumber);
		if (student == null)
			throw new StudentNotFoundException(Helper.NO_STUDENT_FOUND_WITH_MOBILE_NUMBER + mobileNumber);
		return student;
	}
	
	
	
	//checking the email
	public List<Student> checkStudentEmail(String email)    {
	    List<Student> student = studentDao.findByEmail(email);
	    System.out.print(student);
	    return(student);
	}
	
	public List<Warden> checkWardenEmail(String email)    {
	    List<Warden> warden = wardenDao.findByEmail(email);
	    System.out.print(warden);
	    return(warden);
	}


}
