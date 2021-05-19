package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entities.FeeStructure;

@Repository
public interface IFeeStructureDao extends JpaRepository<FeeStructure, Integer> {

	public List<FeeStructure> findByPaymentStatus(String string);

	@Query("from FeeStructure fs inner join fs.student s inner join fs.allotment a where s.id = :student_id")
	public FeeStructure getFeeStructure(@Param("student_id") Integer studentId);

}
