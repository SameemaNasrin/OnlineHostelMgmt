package com.cg.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.cg.dao.IFeeStructDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.FeeStruct;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

@Service
public class FeeStructServiceImpl implements IFeeStructService{


	@Override
	public boolean payFeeByStudentId(IStudentDao studentDao) throws StudentNotFoundException {

		return false;
	}

	@Override
	public List<FeeStruct> viewAllDefaulter(IFeeStructDao feeStructDao) throws AllotmentNotFoundException {
return null;
	}
	

}
