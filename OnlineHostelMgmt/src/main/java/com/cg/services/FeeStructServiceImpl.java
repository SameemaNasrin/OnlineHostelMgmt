package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.dao.IFeeStructDao;
import com.cg.entities.FeeStruct;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

@Service
public class FeeStructServiceImpl implements IFeeStructService{


	@Override
	public boolean payFeeByStudentId(Student student) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public List<FeeStruct> viewAllDefaulter(IFeeStructDao feeStructDao) throws AllotmentNotFoundException {
		Optional<FeeStruct> optFeeStruct = null;
	/*	if(feeStructDao.getPaymentStatus=="In Progress")
		{

		}
		if(!optFeeStruct.isPresent()) {
			throw new AllotmentNotFoundException("No allotment data");
		}*/
		return (List<FeeStruct>) optFeeStruct.get();
	}
	

}
