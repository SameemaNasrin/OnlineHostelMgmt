package com.cg.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IStudentDao;
import com.cg.dto.FeeStructureDto;
import com.cg.entities.Allotment;
import com.cg.entities.FeeStructure;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.FeeStructureNotFoundException;
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

	@Override
	public Integer payFeeByStudentId(Integer studentId) // need to check later
			throws StudentNotFoundException, AllotmentNotFoundException {

		Student student = studentDao.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Student not found by id " + studentId));
//		FeeStructure feeStructure = feeStructureDao.getFeeStructure(studentId);
		FeeStructure feeStructure = feeStructureDao.findByStudentId(student.getId());
//		feeStructure.setStudent(student);
//
//		feeStructure.setTotalFees(fsDto.getTotalFees());
		feeStructure.setPaymentStatus(Helper.PAID);
		feeStructure.setPaymentDate(LocalDate.now());

//		Allotment allotment = allotmentDao.findByStudentId(studentId);
//
//		if (allotment == null)
//			throw new AllotmentNotFoundException("Allotment not found for student Id " + studentId);
//
//		feeStructure.setAllotment(allotment);

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
