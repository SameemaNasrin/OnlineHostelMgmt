package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.FeeStructure;

@Repository
public interface IFeeStructureDao extends JpaRepository<FeeStructure, Integer>{

	List<FeeStructure> findByPaymentStatus(String string);

	

}
