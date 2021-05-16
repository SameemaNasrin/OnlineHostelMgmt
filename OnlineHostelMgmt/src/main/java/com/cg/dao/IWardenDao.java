package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Warden;

public interface IWardenDao extends JpaRepository<Warden, Integer> {
	

}
