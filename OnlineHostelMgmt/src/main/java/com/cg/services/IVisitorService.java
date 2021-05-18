package com.cg.services;

import java.time.LocalDate;
import java.util.List;

import com.cg.dto.VisitorDTO;
import com.cg.entities.Visitor;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.VisitorNotFoundException;

public interface IVisitorService {
	public Visitor addVisitor(VisitorDTO visitorDto) throws StudentNotFoundException, HostelNotFoundException;

	public List<Visitor> getVisitorByVisitDate(LocalDate visitDate) throws VisitorNotFoundException;

	public List<Visitor> getVisitorByDateOfVisitingAndHostel(LocalDate visitDate, Long hostel_Id)
			throws VisitorNotFoundException, HostelNotFoundException;

	public List<Visitor> getVisitorByStudent(Integer student_Id)
			throws VisitorNotFoundException, StudentNotFoundException;

}
