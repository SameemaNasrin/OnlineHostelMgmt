package com.cg.services;

import java.util.List;

import com.cg.dao.IFeeStructDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.FeeStruct;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

public interface IFeeStructService {
	public boolean payFeeByStudentId(IStudentDao studentDao) throws StudentNotFoundException;
	public List<FeeStruct> viewAllDefaulter(IFeeStructDao feeStructDao) throws AllotmentNotFoundException;
}
