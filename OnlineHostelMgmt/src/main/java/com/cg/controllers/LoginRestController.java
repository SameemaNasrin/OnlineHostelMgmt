package com.cg.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.LoginDto;
import com.cg.dto.LoginResponse;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Login;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.*;
import com.cg.services.ILoginService;
import com.cg.services.IStudentService;
import com.cg.helper.LoginConstants;

/*
 * @Author: Supriyo Das
 */
@RestController
@CrossOrigin("*")
public class LoginRestController {

	@Autowired
	private ILoginService service;
	@Autowired
	private IStudentService studentService;

	Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	/*
	 * Controller Method for Login
	 */
	/**
	 * @param logindto
	 * @param br
	 * @return
	 * @throws LoginException
	 * @throws ValidateLoginException
	 */
	@PostMapping("login")
	public Map<String, Object> doLoginController(@Valid @RequestBody LoginDto logindto, BindingResult br)
			throws LoginException, ValidateLoginException {
		
//		
		if (!service.getAuthMap().isEmpty())
			throw new LoginException(LoginConstants.ALREADY_LOGGED_IN);

		if (br.hasErrors())
			throw new ValidateLoginException(br.getFieldErrors());
		Map<String, Object> map = new HashMap<>();
		map.put("email", null);
		map.put("username", null);
		map.put("gender", null);
		map.put("dob", null);
		map.put("mobile", null);
		map.put("address", null);
		map.put("guardian", null);
		map.put("hostel", null);
		map.put("role", null);
		Login login = service.doLogin(logindto.getEmail(), logindto.getPassword(), logindto.getRole());
		if (login.getAdmin() != null) {
			map.put("email", login.getAdmin().getEmail());
			map.put("username", "admin");
			map.put("role", "admin");
			map.put("id", login.getAdmin().getId());
		} else if (login.getStudent() != null) {
			map.put("email", login.getStudent().getEmail());
			map.put("username", login.getStudent().getName());
			map.put("gender", login.getStudent().getGender());
			map.put("dob", login.getStudent().getDob());
			map.put("mobile", login.getStudent().getMobile());
			map.put("address", login.getStudent().getAddress());
			map.put("guardian", login.getStudent().getGuardianName());
			map.put("role", "student");
			map.put("id", login.getStudent().getId());
		} else if (login.getWarden() != null) {
			map.put("email", login.getWarden().getEmail());
			map.put("username", login.getWarden().getName());

			map.put("hostel", login.getWarden().getHostel());
			map.put("role", "warden");
			map.put("id", login.getWarden().getId());
		}
		map.put("token", service.generateToken(login));
		logger.info(service.getAuthMap().toString());
		return map;
//		LoginResponse response = new LoginResponse();
//		response.setToken(service.generateToken(login));
//		response.setEmail(login.getEmail());
//		response.setRole(login.getRole());
//		return response;
	}

	/*
	 * Controller method for logging out
	 */
	@PostMapping("logout")
	public SuccessMessage logout(@RequestHeader("token-id") String token, HttpServletRequest req)
			throws LoginException {

		if (!service.getAuthMap().containsKey(token)) {
			throw new LoginException("Invalid token");
		}
		service.getAuthMap().remove(token);
		logger.info(service.getAuthMap().toString());
		return new SuccessMessage(LoginConstants.LOGGED_OUT);

	}

	@GetMapping("getloggedininfo")
	public Map<String, Object> getLoggedInInfo(@RequestHeader("token-id") String token, HttpServletRequest req)
			throws LoginException {
		if (!service.getAuthMap().containsKey(token)) {
			throw new LoginException("Invalid token");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("email", null);
		map.put("username", null);
		map.put("gender", null);
		map.put("dob", null);
		map.put("mobile", null);
		map.put("address", null);
		map.put("guardian", null);
		map.put("hostel", null);
		map.put("role", null);
		Login login = service.getAuthMap().get(token);
		if (login.getAdmin() != null) {
			map.put("email", login.getAdmin().getEmail());
			map.put("username", "admin");
			map.put("role", "admin");
		} else if (login.getStudent() != null) {
			map.put("email", login.getStudent().getEmail());
			map.put("username", login.getStudent().getName());
			map.put("gender", login.getStudent().getGender());
			map.put("dob", login.getStudent().getDob());
			map.put("mobile", login.getStudent().getMobile());
			map.put("address", login.getStudent().getAddress());
			map.put("guardian", login.getStudent().getGuardianName());
			map.put("role", "student");

		} else if (login.getWarden() != null) {
			map.put("email", login.getWarden().getEmail());
			map.put("username", login.getWarden().getName());

			map.put("hostel", login.getWarden().getHostel());
			map.put("role", "warden");
		}
		return map;

	}

}
