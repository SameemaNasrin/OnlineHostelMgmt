package com.cg.services;

/*
 * @Author: Supriyo Das
 * @Created at: 20.05.2021
 * */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IAdminDao;
import com.cg.dao.ILoginDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.StudentDTO;
import com.cg.entities.Admin;
import com.cg.entities.Login;
import com.cg.entities.Student;
import com.cg.entities.Warden;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.MobileNumberAlreadyExistsException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.helper.Helper;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	IStudentDao studentDao;

	@Autowired
	IWardenDao wardenDao;

	@Autowired
	ILoginDao loginDao;

	@Autowired
	IAdminDao adminDao;

	@Override
	public Map<String, String> addStudent(StudentDTO studentDto)
			throws EmailAlreadyExistException, MobileNumberAlreadyExistsException {
		Student student = new Student();
		student.setName(studentDto.getName());
		/*
		 * checking for unique email run a function-> return type- list of email if
		 * returned list is empty then add student else if return list is not empty then
		 * throw exception that the email is already registered
		 */
		List<Student> studentEmail = checkStudentEmail(studentDto.getEmail());
		List<Warden> wardenEmail = checkWardenEmail(studentDto.getEmail());
		List<Admin> adminEmail = checkAdminEmail(studentDto.getEmail());
		List<Student> studentPhone = checkStudentPhone(studentDto.getMobile());

		if (studentEmail.size() > 0 || wardenEmail.size() > 0 || adminEmail.size() > 0)
			throw new EmailAlreadyExistException("This Email is already registered");
		if (studentPhone.size() > 0)
			throw new MobileNumberAlreadyExistsException("This mobile number is already registered");

		Map<String, String> output = new HashMap<>();
		student.setEmail(studentDto.getEmail());
		student.setGender(studentDto.getGender());
		student.setAddress(studentDto.getAddress());
		student.setDob(studentDto.getDob());
		student.setGuardianName(studentDto.getGuardianName());
		student.setMobile(studentDto.getMobile());
		Student savedStudent = studentDao.save(student);
		String password = savedStudent.getName().substring(0, 3) + "-" + savedStudent.getId();
		String encryptedPassword = LoginServiceImpl.encryptPassword(password);

		Login login = new Login();
		login.setEmail(student.getEmail());
		login.setPassword(encryptedPassword);
		login.setRole("student");
		login.setStudent(savedStudent);
		loginDao.save(login);

		output.put("studentId", String.valueOf(savedStudent.getId()));
		output.put("password", password);

		return output;
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
		Student student = studentDao.findByMobile(mobileNumber).get(0);
		if (student == null)
			throw new StudentNotFoundException(Helper.NO_STUDENT_FOUND_WITH_MOBILE_NUMBER + mobileNumber);
		return student;
	}

	// checking the email
	private List<Admin> checkAdminEmail(String email) {
		List<Admin> admins = adminDao.findByEmail(email);

		return admins;
	}

	private List<Student> checkStudentEmail(String email) {
		List<Student> students = studentDao.findByEmail(email);

		return students;
	}

	private List<Warden> checkWardenEmail(String email) {
		List<Warden> wardens = wardenDao.findByEmail(email);

		return wardens;
	}

	private List<Student> checkStudentPhone(String phone) {
		List<Student> students = studentDao.findByMobile(phone);
		return students;
	}

	@Override
	public List<Student> getUnallottedStudents() throws StudentNotFoundException {
		List<Student> students = studentDao.findUnallottedStudents();
		if (students.isEmpty())
			throw new StudentNotFoundException("No unallocated students");
		return students;
	}

	@Override
	public List<Student> getAllottedStudents() throws StudentNotFoundException {
		List<Student> students = studentDao.findAllottedStudents();
		if (students.isEmpty())
			throw new StudentNotFoundException("No Allocated students");
		return students;
	}

}
