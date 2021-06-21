package com.cg.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.entities.FeeStructure;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.IncorrectAmountException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.IFeeStructService;

@RestController
@RequestMapping("/feestructure")
@CrossOrigin("*")
public class FeeStructRestController {

	@Autowired
	IFeeStructService feeStructureService;
	Logger logger = LoggerFactory.getLogger(FeeStructRestController.class);

	@PostMapping("/pay/{sid}")
	public SuccessMessage payFeeByStudentId(@RequestParam Double amount, @PathVariable("sid") Integer studentId)
			throws StudentNotFoundException, AllotmentNotFoundException, IncorrectAmountException {

		Integer id = feeStructureService.payFeeByStudentId(studentId,amount);
		return new SuccessMessage("Your generated ID is " + id);
	}

	@GetMapping("/viewunpaid")
	public List<FeeStructure> viewUnpaidStudents() throws FeeStructureNotFoundException {
		return feeStructureService.viewAllDefaulter();
	}

	@GetMapping("/viewbystudent/{sid}")
	public List<FeeStructure> viewFeeByStudentId(@PathVariable("sid") Integer studentId)
			throws StudentNotFoundException, FeeStructureNotFoundException {
		return feeStructureService.viewFeeByStudentId(studentId);
	}

}
