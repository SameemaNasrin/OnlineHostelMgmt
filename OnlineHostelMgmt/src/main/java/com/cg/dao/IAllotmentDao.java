package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Allotment;

public interface IAllotmentDao extends JpaRepository<Allotment, Integer>{

}
