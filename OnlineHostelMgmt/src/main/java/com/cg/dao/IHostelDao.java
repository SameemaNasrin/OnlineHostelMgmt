package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Hostel;
import com.cg.entities.Room;

@Repository
public interface IHostelDao extends JpaRepository<Hostel, Long> {

}
