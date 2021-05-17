package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.dto.WardenDto;
import com.cg.entities.Warden;
import com.cg.exceptions.ValidateWardenException;
import com.cg.exceptions.WardenNotFoundException;
import com.cg.services.IWardenService;

@RestController
public class WardenRestController {
	
	@Autowired
	IWardenService wardenService;
	
	@GetMapping("/get")
	public List<Warden> viewAllWarden() throws WardenNotFoundException {
		return wardenService.viewAllWarden();
	}

	@GetMapping("/get/{wid}")
	public Warden viewWardenByWId(@PathVariable("wid") Integer wid) throws WardenNotFoundException {
		return wardenService.viewWardenByWId(wid);
	}
	
	@PostMapping("/add")
	public SuccessMessage addWarden(@Valid @RequestBody WardenDto wardenDto, BindingResult br) throws ValidateWardenException{	
		
		if (br.hasErrors()) {
			throw new ValidateWardenException(br.getFieldErrors());
		}
		Integer wardenId = wardenService.addWarden(wardenDto);
		return new SuccessMessage("Your generated ID is " + wardenId);
		
	}
	
	
	

}
