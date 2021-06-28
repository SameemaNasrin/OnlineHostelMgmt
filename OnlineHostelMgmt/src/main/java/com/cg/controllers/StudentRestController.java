package com.cg.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.StudentDTO;
import com.cg.dto.SuccessMessage;
import com.cg.entities.Student;
import com.cg.exceptions.EmailAlreadyExistException;
import com.cg.exceptions.MobileNumberAlreadyExistsException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.ValidateStudentException;
import com.cg.services.IStudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentRestController {

	@Autowired
	IStudentService studentService;

	@PostMapping("/add")
	public SuccessMessage addStudent(@Valid @RequestBody StudentDTO studentDto, BindingResult br)
			throws ValidateStudentException, EmailAlreadyExistException, MobileNumberAlreadyExistsException {
		if (br.hasErrors()) {
			throw new ValidateStudentException(br.getFieldErrors());
		}
		Map<String, String> outputMap = studentService.addStudent(studentDto);

		return new SuccessMessage("Your generated ID is " + outputMap.get("studentId") + " and Password is: " + outputMap.get("password"));

	}

	@GetMapping("/get")
	public ResponseEntity<List<Student>> viewStudents() throws StudentNotFoundException {
		return new ResponseEntity<List<Student>>(studentService.getStudents(), HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Student> viewStudentById(@PathVariable("id") Integer id) throws StudentNotFoundException {
		return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
	}

	@GetMapping("/get/name/{name}")
	public ResponseEntity<List<Student>> viewStudentsByName(@PathVariable("name") String name)
			throws StudentNotFoundException {
		return new ResponseEntity<List<Student>>(studentService.getStudentByName(name), HttpStatus.OK);
	}

	@GetMapping("/get/mobile/{mobile}")
	public ResponseEntity<Student> viewStudentByMobileNumber(@PathVariable("mobile") String mobile)
			throws StudentNotFoundException {
		return new ResponseEntity<Student>(studentService.getStudentByMobileNumber(mobile), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public SuccessMessage remoteStudentById(@PathVariable("id") Integer id) throws StudentNotFoundException {
		Integer removedStudentId = studentService.removeStudentById(id);
		return new SuccessMessage("Student with id " + removedStudentId + " has been removed successfully");
	}
	
	@GetMapping("/get/unallotted")
	public ResponseEntity<List<Student>> viewUnallottedStudents() throws StudentNotFoundException {
		return new ResponseEntity<List<Student>>(studentService.getUnallottedStudents(),HttpStatus.OK);
	}
	
	@GetMapping("/get/allotted")
	public ResponseEntity<List<Student>> viewAllottedStudents() throws StudentNotFoundException {
		return new ResponseEntity<List<Student>>(studentService.getAllottedStudents(),HttpStatus.OK);
	}
	
	
}
