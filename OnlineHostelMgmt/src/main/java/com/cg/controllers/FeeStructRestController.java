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

import com.cg.dto.FeeStructureDto;
import com.cg.dto.SuccessMessage;
import com.cg.entities.FeeStructure;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.ValidateFeeStructureException;
import com.cg.services.IFeeStructService;

@RestController
@RequestMapping("feestructure")
public class FeeStructRestController {

	@Autowired
	IFeeStructService feeStructureService;
	

	@PostMapping("add/{sid}")
	public SuccessMessage payFeeByStudentId(@PathVariable("sid") Integer studentId,
			@Valid @RequestBody FeeStructureDto fsDto, BindingResult br)
			throws StudentNotFoundException, AllotmentNotFoundException, ValidateFeeStructureException {
		if (br.hasErrors()) {
			throw new ValidateFeeStructureException(br.getFieldErrors());
		}
		Integer id = feeStructureService.payFeeByStudentId(studentId, fsDto);
		return new SuccessMessage("Your generated ID is " + id);
	}

	@GetMapping("/viewunpaid")
	public List<FeeStructure> viewEmployeebyId() throws FeeStructureNotFoundException {
		return feeStructureService.viewAllDefaulter();
	}
	@GetMapping("/viewbystudent/{sid}")
	List<FeeStructure> viewFeeByStudentId(@PathVariable("sid")Integer studentId) throws StudentNotFoundException{
		return feeStructureService.viewFeeByStudentId(studentId);
	}

}
