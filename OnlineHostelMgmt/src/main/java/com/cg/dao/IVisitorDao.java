package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Visitor;

@Repository
public interface IVisitorDao extends JpaRepository<Visitor, Integer> {

	public List<Visitor> findByDateOfVisiting(LocalDate dateOfVisiting);

	public List<Visitor> findByStudentId(Integer studentId);

	public List<Visitor> findByDateOfVisitingAndHostel_Id(LocalDate date, Long Hostel_id);

}
