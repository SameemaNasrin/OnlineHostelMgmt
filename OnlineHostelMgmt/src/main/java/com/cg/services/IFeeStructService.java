package com.cg.services;

import java.util.List;

import com.cg.dto.FeeStructureDto;
import com.cg.entities.FeeStructure;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

public interface IFeeStructService {
	List<FeeStructure> viewAllDefaulter() throws FeeStructureNotFoundException;
	Integer payFeeByStudentId(Integer studentId, FeeStructureDto fsDto) throws StudentNotFoundException, AllotmentNotFoundException;
}
