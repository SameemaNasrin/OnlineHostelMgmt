package com.cg.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.dto.WardenDto;
import com.cg.entities.Warden;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.ValidateWardenException;
import com.cg.exceptions.WardenNotFoundException;
import com.cg.services.IWardenService;

@RestController
@RequestMapping("/warden")
@CrossOrigin("*")
public class WardenRestController {

	@Autowired
	IWardenService wardenService;

	@GetMapping("/get")
	public List<Warden> viewAllWarden() throws WardenNotFoundException {
		return wardenService.viewAllWarden();
	}

	@GetMapping("/get/{wid}")
	public Warden viewWardenByWId(@PathVariable("wid") Integer wardenId) throws WardenNotFoundException {
		return wardenService.viewWardenByWardenId(wardenId);
	}

	@GetMapping("/get/hostel/{hid}")
	public List<Warden> viewWardenByHostelId(@PathVariable("hid") Integer hostelId) throws WardenNotFoundException,HostelNotFoundException {
		return wardenService.viewWardenByHostelId(hostelId);
	}

	@PostMapping("/add")
	public SuccessMessage addWarden(@Valid @RequestBody WardenDto wardenDto, BindingResult br)
			throws ValidateWardenException, HostelNotFoundException, EmailAlreadyExistException {

		if (br.hasErrors()) {
			throw new ValidateWardenException(br.getFieldErrors());
		}
		Map<String, String> outputMap = wardenService.addWarden(wardenDto);

		return new SuccessMessage("Your generated ID is " + outputMap.get("wardenId") + " and Password is: " + outputMap.get("password"));

	}

}
