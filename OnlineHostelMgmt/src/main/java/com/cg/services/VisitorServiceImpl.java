package com.cg.services;
/*
 * @Author: Frazia Sajadin
 * @Created at: 22.05.2021
 * */
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IHostelDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IVisitorDao;
import com.cg.dto.VisitorDTO;
import com.cg.entities.Hostel;
import com.cg.entities.Student;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotAllottedException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.VisitorNotFoundException;
import com.cg.helper.Helper;

@Service
public class VisitorServiceImpl implements IVisitorService {
	@Autowired
	IVisitorDao visitorDao;
	@Autowired
	IStudentDao studentDao;
	@Autowired
	IHostelDao hostelDao;

	@Override
	public Visitor addVisitor(VisitorDTO visitorDto) throws StudentNotFoundException, HostelNotFoundException, StudentNotAllottedException {
		Student student = studentDao.findById(visitorDto.getStudentId()).orElseThrow(
				() -> new StudentNotFoundException(Helper.NO_STUDENT_FOUND_WITH_ID + visitorDto.getStudentId()));
		if(student.getAllotment() == null) {
			throw new StudentNotAllottedException("Student not allotted");
		}
		Hostel hostel = student.getAllotment().getHostel();
		Visitor visitor = new Visitor();
		visitor.setVisitorName(visitorDto.getName());
		visitor.setContactNumber(visitorDto.getNumber());
		visitor.setStudent(student);
		visitor.setStudentRelation(visitorDto.getStudentRelation());
		visitor.setVisitorAddress(visitorDto.getVisitorAddress());
		visitor.setReason(visitorDto.getReason());
		visitor.setDateOfVisiting(visitorDto.getDateOfVisiting());
		visitor.setHostel(hostel);
		return visitorDao.save(visitor);
	}

	@Override
	public List<Visitor> getVisitorByVisitDate(LocalDate visitDate) throws VisitorNotFoundException {
		List<Visitor> visitorList = visitorDao.findByDateOfVisiting(visitDate);
		if (visitorList.isEmpty())
			throw new VisitorNotFoundException("No visitors found on " + visitDate);
		return visitorList;
	}

	@Override
	public List<Visitor> getVisitorByDateOfVisitingAndHostel(LocalDate visitDate, Integer hostel_Id)
			throws VisitorNotFoundException, HostelNotFoundException {
		hostelDao.findById(hostel_Id)
				.orElseThrow(() -> new HostelNotFoundException(Helper.NO_HOSTEL_FOUND_WITH_ID + hostel_Id));
		List<Visitor> visitorList = visitorDao.findByDateOfVisitingAndHostel_Id(visitDate, hostel_Id);
		if (visitorList.isEmpty())
			throw new VisitorNotFoundException("No visitors found");
		return visitorList;
	}

	@Override
	public List<Visitor> getVisitorByStudent(Integer student_Id)
			throws VisitorNotFoundException, StudentNotFoundException {
		studentDao.findById(student_Id)
				.orElseThrow(() -> new StudentNotFoundException("No student found with id: " + student_Id));
		List<Visitor> visitorList = visitorDao.findByStudentId(student_Id);
		if (visitorList.isEmpty())
			throw new VisitorNotFoundException("No visitors found");
		return visitorList;
	}

	@Override
	public List<Visitor> getAllVisitors() throws VisitorNotFoundException {
		List<Visitor> visitorList = visitorDao.findAll();
		if (visitorList.isEmpty()) {
			throw new VisitorNotFoundException(Helper.NO_VISITORS_FOUND);
		}

		return visitorList;
	}

}
