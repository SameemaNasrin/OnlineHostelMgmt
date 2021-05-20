package com.cg.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.FeeStructure;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
import com.cg.exceptions.IncorrectAmountException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.helper.Helper;

@Service
public class FeeStructureServiceImpl implements IFeeStructService {

	@Autowired
	IStudentDao studentDao;

	@Autowired
	IAllotmentDao allotmentDao;

	@Autowired
	IFeeStructureDao feeStructureDao;

	Logger logger = LoggerFactory.getLogger(FeeStructureServiceImpl.class);
	
	@Override
	public Integer payFeeByStudentId(Integer studentId, Double amount) // need to check later
			throws StudentNotFoundException, AllotmentNotFoundException,IncorrectAmountException {

		Student student = studentDao.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found by id " + studentId));
//		FeeStructure feeStructure = feeStructureDao.getFeeStructure(studentId);
		FeeStructure feeStructure = feeStructureDao.findByStudentId(student.getId());
		logger.info(feeStructure.getTotalFees().toString());
		logger.info(amount.toString());
		

		if(!feeStructure.getTotalFees().equals(amount)) {
			throw new IncorrectAmountException("The amount should be " + feeStructure.getTotalFees());
		}
		feeStructure.setPaymentStatus(Helper.PAID);
		feeStructure.setPaymentDate(LocalDate.now());



		return feeStructureDao.save(feeStructure).getId();
	}

	@Override
	public List<FeeStructure> viewAllDefaulter() throws FeeStructureNotFoundException {
		List<FeeStructure> defaulters = feeStructureDao.findByPaymentStatus(Helper.NOT_PAID);

		if (defaulters.isEmpty())
			throw new FeeStructureNotFoundException("No unpaid Fee Structure found");

		return defaulters;
	}

	@Override
	public List<FeeStructure> viewFeeByStudentId(Integer studentId)
			throws StudentNotFoundException, FeeStructureNotFoundException {
		Student student = studentDao.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found by id " + studentId));
		
		FeeStructure fs = feeStructureDao.getFeeStructure(studentId);
		if(fs == null) {
			throw new FeeStructureNotFoundException("No fee structure found for student id " + student.getId());
		}
		List<FeeStructure> feeList = new ArrayList<>();
		feeList.add(fs);

		return feeList;
	}
}
