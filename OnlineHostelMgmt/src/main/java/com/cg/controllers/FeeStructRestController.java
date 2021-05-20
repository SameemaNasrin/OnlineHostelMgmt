package com.cg.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.entities.FeeStructure;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.IFeeStructService;

@RestController
@RequestMapping("/feestructure")
public class FeeStructRestController {

	@Autowired
	IFeeStructService feeStructureService;
	Logger logger = LoggerFactory.getLogger(FeeStructRestController.class);
	@PostMapping("/pay/{sid}")//works fine but gives error if multiple entry with same student id is there.
	public SuccessMessage payFeeByStudentId(@RequestBody Double amount, @PathVariable("sid") Integer studentId)
			throws StudentNotFoundException, AllotmentNotFoundException {

		logger.info("here here");
//		Integer id = feeStructureService.payFeeByStudentId(studentId);
		int id = 2;
		return new SuccessMessage("Your generated ID is " + id);
	}

	@GetMapping("/viewunpaid")//works
	public List<FeeStructure> viewUnpaidStudents() throws FeeStructureNotFoundException {
		return feeStructureService.viewAllDefaulter();
	}

	@GetMapping("/viewbystudent/{sid}")//works
	List<FeeStructure> viewFeeByStudentId(@PathVariable("sid") Integer studentId)
			throws StudentNotFoundException, FeeStructureNotFoundException {
		return feeStructureService.viewFeeByStudentId(studentId);
	}

}
