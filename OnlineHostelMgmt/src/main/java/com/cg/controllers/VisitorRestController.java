package com.cg.controllers;

import java.time.LocalDate;
import java.util.List;

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
import com.cg.dto.VisitorDTO;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.ValidateVisitorException;
import com.cg.exceptions.VisitorNotFoundException;
import com.cg.services.IVisitorService;

@RestController
@RequestMapping("/visitor")
@CrossOrigin("*")
public class VisitorRestController {

	@Autowired
	IVisitorService visitorService;

	@PostMapping("/add")
	public SuccessMessage addVisitor(@Valid @RequestBody VisitorDTO visitorDto, BindingResult br)
			throws StudentNotFoundException, HostelNotFoundException, ValidateVisitorException {
		if(br.hasErrors()) {
			throw new ValidateVisitorException(br.getFieldErrors());
		}
		Integer visitorId = visitorService.addVisitor(visitorDto).getId();
		return new SuccessMessage("Visitor created with id: " + visitorId);
	}

	@GetMapping("/get/{date}")
	public ResponseEntity<List<Visitor>> viewVisitorByVisitDate(@PathVariable("date") String date)
			throws VisitorNotFoundException {
		LocalDate visitDate = LocalDate.parse(date);
		return new ResponseEntity<List<Visitor>>(visitorService.getVisitorByVisitDate(visitDate), HttpStatus.OK);
	}

	@GetMapping("/get/date/{date}/{id}")
	public ResponseEntity<List<Visitor>> viewVisitorByDateAndHoste(@PathVariable("date") String date,
			@PathVariable("id") Integer hostel_Id) throws VisitorNotFoundException, HostelNotFoundException {
		LocalDate visitDate = LocalDate.parse(date);
		return new ResponseEntity<List<Visitor>>(
				visitorService.getVisitorByDateOfVisitingAndHostel(visitDate, hostel_Id), HttpStatus.OK);
	}

	@GetMapping("/get/student/{sId}")
	public ResponseEntity<List<Visitor>> viewVisitorByStudent(@PathVariable("sId") Integer student_Id)
			throws VisitorNotFoundException, StudentNotFoundException {
		return new ResponseEntity<List<Visitor>>(visitorService.getVisitorByStudent(student_Id), HttpStatus.OK);
	}

}
