package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Allotment;

@Repository
public interface IAllotmentDao extends JpaRepository<Allotment, Integer>{

	List<Allotment> findByRoomId(Integer roomId);

}
