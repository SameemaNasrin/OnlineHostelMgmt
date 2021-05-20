package com.cg.services;

import java.util.List;

import com.cg.entities.FeeStructure;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

public interface IFeeStructService {
	public List<FeeStructure> viewAllDefaulter() throws FeeStructureNotFoundException;

	public Integer payFeeByStudentId(Integer studentId) throws StudentNotFoundException, AllotmentNotFoundException;

	public List<FeeStructure> viewFeeByStudentId(Integer studentId)
			throws StudentNotFoundException, FeeStructureNotFoundException;
}
