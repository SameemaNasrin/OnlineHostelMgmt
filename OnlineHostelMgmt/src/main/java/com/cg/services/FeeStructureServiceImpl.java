package com.cg.services;

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
import com.cg.exceptions.StudentNotFoundException;

@Service
public class FeeStructureServiceImpl implements IFeeStructService{

	@Autowired
	IStudentDao studentDao;
	
	@Autowired
	IAllotmentDao allotmentDao;
	
	@Autowired
	IFeeStructureDao feeStructureDao;
	
	@Override
	public Integer payFeeByStudentId(Integer studentId, FeeStructureDto fsDto) throws StudentNotFoundException, AllotmentNotFoundException {		
		FeeStructure feeStructure = new FeeStructure();
		
		Student student = studentDao.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not found by id " + studentId));
		feeStructure.setStudent(student);
		
		feeStructure.setTotalFees(fsDto.getTotalFees());
		
		Allotment allotment = allotmentDao.findByStudentId(studentId);
		
		if(allotment == null)
			throw new AllotmentNotFoundException("Allotment not found for student Id " + studentId);
		
		feeStructure.setAllotment(allotment);
		
		feeStructure.setPaymentStatus(fsDto.getPaymentStatus());
		feeStructure.setPaymentDate(fsDto.getPaymentDate());//LocalDate.now()
		
		return feeStructureDao.save(feeStructure).getId();
	}

	@Override
	public List<FeeStructure> viewAllDefaulter() throws AllotmentNotFoundException {
		
		return null;
	}


	

}
