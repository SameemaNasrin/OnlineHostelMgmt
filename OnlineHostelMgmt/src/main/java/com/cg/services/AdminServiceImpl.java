package com.cg.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IAdminDao;
import com.cg.dao.ILoginDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.AdminDto;
import com.cg.entities.Admin;
import com.cg.entities.Login;
import com.cg.entities.Student;
import com.cg.entities.Warden;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.MobileNumberAlreadyExistsException;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	IStudentDao studentDao;

	@Autowired
	IWardenDao wardenDao;

	@Autowired
	ILoginDao loginDao;
	
	@Autowired
	IAdminDao adminDao;

	@Override
	public Map<String, String> addAdmin(AdminDto adminDto) throws EmailAlreadyExistException {
		Admin admin = new Admin();

		List<Student> studentEmail = checkStudentEmail(adminDto.getEmail());
		List<Warden> wardenEmail = checkWardenEmail(adminDto.getEmail());
		List<Admin> adminEmail = checkAdminEmail(adminDto.getEmail());

		if (studentEmail.size() > 0 || wardenEmail.size() > 0 || adminEmail.size() > 0)
			throw new EmailAlreadyExistException("This Email is already registered");


		Map<String, String> output = new HashMap<>();
		admin.setEmail(adminDto.getEmail());

		Admin savedAdmin = adminDao.save(admin);
		String password = adminDto.getPassword();
		String encryptedPassword = LoginServiceImpl.encryptPassword(password);

		Login login = new Login();
		login.setEmail(savedAdmin.getEmail());
		login.setPassword(encryptedPassword);
		login.setRole("admin");
		login.setAdmin(savedAdmin);
		loginDao.save(login);
		output.put("adminId", String.valueOf(savedAdmin.getId()));
		output.put("password", password);

		return output;
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

	

}
