package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Warden;

@Repository
public interface IWardenDao extends JpaRepository<Warden, Integer> {

	public List<Warden> findByHostelId(Integer hostelId);


}
