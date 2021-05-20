package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Hostel;

@Repository
public interface IHostelDao extends JpaRepository<Hostel, Long> {

}
