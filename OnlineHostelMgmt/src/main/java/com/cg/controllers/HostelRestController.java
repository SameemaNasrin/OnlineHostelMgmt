package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.HostelDto;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.ValidateHostelException;
import com.cg.services.IHostelService;

@RestController
@RequestMapping("/hostel")
public class HostelRestController {
	
	@Autowired
	IHostelService hostelService;

	@GetMapping("/get")
	public List<Hostel> viewAllHostel() throws HostelNotFoundException {
		return hostelService.viewAllHostel();
	}
	
	@GetMapping("/get/{hid}")
	public Hostel viewEmployeebyId(@PathVariable("hid") Integer hostelId) throws HostelNotFoundException {
		return hostelService.viewHostelById(hostelId);
	}

	@PostMapping("/add")
	public SuccessMessage addHostel(@Valid @RequestBody HostelDto hostelDto, BindingResult br) throws ValidateHostelException{	
		
		if (br.hasErrors()) {
			throw new ValidateHostelException(br.getFieldErrors());
		}
		Integer hostelId = hostelService.addHostel(hostelDto);
		return new SuccessMessage("Your generated ID is " + hostelId);
		
	}
}
