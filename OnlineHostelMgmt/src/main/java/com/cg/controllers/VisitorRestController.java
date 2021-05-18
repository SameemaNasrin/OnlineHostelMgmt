package com.cg.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.VisitorDTO;
import com.cg.entities.Room;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.VisitorNotFoundException;
import com.cg.services.IVisitorService;

@RestController
@RequestMapping("/visitor")
public class VisitorRestController {
	
	@Autowired
	IVisitorService visitorService;
	
	@PostMapping("/add")
	public ResponseEntity<Visitor> addVisitor(@RequestBody VisitorDTO visitorDto) throws StudentNotFoundException,HostelNotFoundException{
	return new ResponseEntity<Visitor>(visitorService.addVisitor(visitorDto), HttpStatus.CREATED);
	}
	@GetMapping("/get/{Date}")
	public ResponseEntity<List<Visitor>> viewVisitorByVisitDate(@PathVariable("Date")LocalDate visitDate) throws VisitorNotFoundException {
		return new ResponseEntity<List<Visitor>>(visitorService.getVisitorByVisitDate(visitDate), HttpStatus.OK);
	}
	@GetMapping("/get/{Date}/{Id}")
	public ResponseEntity<List<Visitor>> viewVisitorByDateAndHoste(@PathVariable("Date")LocalDate visitDate ,@PathVariable("Id")Long hostel_Id) throws VisitorNotFoundException,HostelNotFoundException {
		return new ResponseEntity<List<Visitor>>(visitorService.getVisitorByDateOfVisitingAndHostel(visitDate,hostel_Id), HttpStatus.OK);
	}
	@GetMapping("/get/{sId}")
	public ResponseEntity<List<Visitor>> viewVisitorByStudent(@PathVariable("sId")Integer student_Id) throws VisitorNotFoundException,StudentNotFoundException{
		return new ResponseEntity<List<Visitor>>(visitorService.getVisitorByStudent(student_Id), HttpStatus.OK);
	}
	
}
