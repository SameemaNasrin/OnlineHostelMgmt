package com.cg.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AdminDto;
import com.cg.dto.SuccessMessage;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.ValidateAdminException;
import com.cg.services.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	IAdminService adminService;

	@PostMapping("/add")
	public SuccessMessage addAdmin(@Valid @RequestBody AdminDto adminDto, BindingResult br)
			throws ValidateAdminException, EmailAlreadyExistException {
		if (br.hasErrors()) {
			throw new ValidateAdminException(br.getFieldErrors());
		}
		Map<String, String> outputMap = adminService.addAdmin(adminDto);

		return new SuccessMessage(
				"Your generated ID is " + outputMap.get("adminId") + " and Password is: " + outputMap.get("password"));

	}
}
