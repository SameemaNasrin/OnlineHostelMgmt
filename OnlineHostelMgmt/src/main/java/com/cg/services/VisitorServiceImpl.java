package com.cg.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.dao.IHostelDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IVisitorDao;
import com.cg.dto.VisitorDTO;
import com.cg.entities.Hostel;
import com.cg.entities.Student;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.VisitorNotFoundException;

public class VisitorServiceImpl implements IVisitorService {
	@Autowired
	IVisitorDao visitorDao;
	@Autowired
	IStudentDao studentDao;
	@Autowired
	IHostelDao hostelDao;

	@Override
	public Visitor addVisitor(VisitorDTO visitorDto) throws StudentNotFoundException, HostelNotFoundException {
		// TODO Auto-generated method stub
		Student student = studentDao.findById(visitorDto.getStudent_id()).orElseThrow(()->new StudentNotFoundException("no student found"));
		Hostel hostel = hostelDao.findById(visitorDto.getHostel_id()).orElseThrow(()->new HostelNotFoundException("No hostel found"));
		Visitor visitor = new Visitor();
        visitor.setVisitorName(visitorDto.getName());
		visitor.setContactNumber(visitorDto.getNumber());
		visitor.setStudent(student);
		visitor.setStudentRelation(visitorDto.getStudentRelation());
		visitor.setVisitorAddress(visitorDto.getVisitorAddress());
		visitor.setReason(visitorDto.getReason());
		visitor.setDateOfVisiting(visitorDto.getDate_of_visiting());
		visitor.setHostel(hostel);
		return visitorDao.save(visitor);
	}
	

	@Override
	public List<Visitor> getVisitorByVisitDate(LocalDate visitDate) throws VisitorNotFoundException {
		// TODO Auto-generated method stub
		List<Visitor> visitorList = new ArrayList<>();
		visitorList = visitorDao.findByDateOfVisiting(visitDate);
		if(visitorList.isEmpty())
			throw new VisitorNotFoundException("visit date not found");
		return visitorList;
	}

	@Override
	public List<Visitor> getVisitorByDateAndHostel(LocalDate visitDate, Long hostel_Id)
			throws VisitorNotFoundException, HostelNotFoundException {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public List<Visitor> getVisitorByStudent(Integer student_Id) throws VisitorNotFoundException, StudentNotFoundException {
		// TODO Auto-generated method stub
		Student student = studentDao.findById(student_Id).orElseThrow(()->new StudentNotFoundException("Student not found with Id: "+student_Id));
		return null;
	}

	
	

}
