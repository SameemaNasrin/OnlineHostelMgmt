package com.cg.services;

import java.util.List;

import com.cg.entities.FeeStructure;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.IncorrectAmountException;
import com.cg.exceptions.StudentNotFoundException;

public interface IFeeStructService {
	public List<FeeStructure> viewAllDefaulter() throws FeeStructureNotFoundException;

	public Integer payFeeByStudentId(Integer studentId, Double amount)
			throws StudentNotFoundException, AllotmentNotFoundException, IncorrectAmountException;

	public List<FeeStructure> viewFeeByStudentId(Integer studentId)
			throws StudentNotFoundException, FeeStructureNotFoundException;
}
