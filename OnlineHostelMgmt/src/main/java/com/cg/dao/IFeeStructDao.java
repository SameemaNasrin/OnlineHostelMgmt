package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.FeeStruct;

public interface IFeeStructDao extends JpaRepository<FeeStruct, Integer>{

}
