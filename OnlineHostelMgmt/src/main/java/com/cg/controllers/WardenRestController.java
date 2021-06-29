package com.cg.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cg.dto.WardenResponse;
import com.cg.entities.Student;
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
	public ResponseEntity<List<WardenResponse>> viewAllWarden() throws WardenNotFoundException {
		List<Warden> wardens = wardenService.viewAllWarden();
		List<WardenResponse> response = new ArrayList<>();
		for(Warden warden:wardens) {
			WardenResponse wardenResponse = new WardenResponse();
			wardenResponse.setId(warden.getId());
			wardenResponse.setName(warden.getName());
			wardenResponse.setEmail(warden.getEmail());
			wardenResponse.setHostel_id(warden.getHostel().getId());
			wardenResponse.setHostel_name(warden.getHostel().getName());
			wardenResponse.setHostel_type(warden.getHostel().getType());
			wardenResponse.setHostel_fee(warden.getHostel().getFee());
			wardenResponse.setHostel_contact(warden.getHostel().getContact());
			wardenResponse.setHostel_address(warden.getHostel().getAddress());
			response.add(wardenResponse);
		}
		return new ResponseEntity<List<WardenResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/get/{wid}")
	public ResponseEntity<WardenResponse> viewWardenByWId(@PathVariable("wid") Integer wardenId) throws WardenNotFoundException {
		Warden warden = wardenService.viewWardenByWardenId(wardenId);
		WardenResponse wardenResponse = new WardenResponse();
		wardenResponse.setId(warden.getId());
		wardenResponse.setName(warden.getName());
		wardenResponse.setEmail(warden.getEmail());
		wardenResponse.setHostel_id(warden.getHostel().getId());
		wardenResponse.setHostel_name(warden.getHostel().getName());
		wardenResponse.setHostel_type(warden.getHostel().getType());
		wardenResponse.setHostel_fee(warden.getHostel().getFee());
		wardenResponse.setHostel_contact(warden.getHostel().getContact());
		wardenResponse.setHostel_address(warden.getHostel().getAddress());
		return new ResponseEntity<WardenResponse>(wardenResponse, HttpStatus.OK);
	}

	@GetMapping("/get/hostel/{hid}")
	public ResponseEntity<List<WardenResponse>> viewWardenByHostelId(@PathVariable("hid") Integer hostelId) throws WardenNotFoundException,HostelNotFoundException {
		List<Warden> wardens = wardenService.viewWardenByHostelId(hostelId);
		List<WardenResponse> response = new ArrayList<>();
		for(Warden warden:wardens) {
			WardenResponse wardenResponse = new WardenResponse();
			wardenResponse.setId(warden.getId());
			wardenResponse.setName(warden.getName());
			wardenResponse.setEmail(warden.getEmail());
			wardenResponse.setHostel_id(warden.getHostel().getId());
			wardenResponse.setHostel_name(warden.getHostel().getName());
			wardenResponse.setHostel_type(warden.getHostel().getType());
			wardenResponse.setHostel_fee(warden.getHostel().getFee());
			wardenResponse.setHostel_contact(warden.getHostel().getContact());
			wardenResponse.setHostel_address(warden.getHostel().getAddress());
			response.add(wardenResponse);
		}
		return new ResponseEntity<List<WardenResponse>>(response, HttpStatus.OK);
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
