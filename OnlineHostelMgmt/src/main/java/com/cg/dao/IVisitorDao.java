package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Visitor;

public interface IVisitorDao extends JpaRepository<Visitor, Integer> {
	
	public List<Visitor> findByDateOfVisiting(LocalDate dateOfVisiting);

}
