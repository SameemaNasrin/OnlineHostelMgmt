package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.HostelDto;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.services.IHostelService;

@RestController
public class HostelRestController {
	
	@Autowired
	IHostelService hostelService;
	
	@GetMapping("viewallhostel")
	public List<Hostel> viewAllHostel() throws HostelNotFoundException {
		return hostelService.viewAllHostel();
	}
	
	@GetMapping("viewhostelbyid/{hid}")
	public Hostel viewEmployeebyId(@PathVariable("hid") Long hostelId) throws HostelNotFoundException {
		return hostelService.viewHostelById(hostelId);
	}

	//http://localhost:8082/addhostel 
	@PostMapping("addhostel")
	public SuccessMessage addHostel(@RequestBody HostelDto hostelDto, BindingResult br){		
		Long hostelId = hostelService.addHostel(hostelDto);
		return new SuccessMessage("Your generated ID is " + hostelId);
		
	}
}
