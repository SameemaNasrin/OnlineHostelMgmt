package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Warden;

@Repository
public interface IWardenDao extends JpaRepository<Warden, Integer> {
	

}
