package com.cg.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.cg.helper.LoginConstants;

/*
 * @Author: Supriyo Das
 */
@RestController
@CrossOrigin("*")
public class LoginRestController {

	@Autowired
	private ILoginService service;

	Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	/*
	 * Controller Method for Login
	 */
	@PostMapping("login")
	public LoginResponse doLoginController(@Valid @RequestBody LoginDto logindto, BindingResult br)
			throws LoginException, ValidateLoginException {
		if (!service.getAuthMap().isEmpty())
			throw new LoginException(LoginConstants.ALREADY_LOGGED_IN);
		if (br.hasErrors())
			throw new ValidateLoginException(br.getFieldErrors());
		Login login = service.doLogin(logindto.getEmail(), logindto.getPassword(), logindto.getRole());
		LoginResponse response = new LoginResponse();
		response.setToken(service.generateToken(login));
		response.setEmail(login.getEmail());
		response.setRole(login.getRole());
		return response;
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

		return new SuccessMessage(LoginConstants.LOGGED_OUT);

	}

}
