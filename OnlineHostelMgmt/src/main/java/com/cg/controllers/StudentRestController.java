package com.cg.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.ValidateStudentException;
import com.cg.services.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	IStudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentDTO studentDto, BindingResult br)
			throws ValidateStudentException {
		if (br.hasErrors()) {
			throw new ValidateStudentException(br.getFieldErrors());
		}

		return new ResponseEntity<Student>(studentService.addStudent(studentDto), HttpStatus.CREATED);

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
}
