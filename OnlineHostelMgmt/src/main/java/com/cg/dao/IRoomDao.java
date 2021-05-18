package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Room;

public interface IRoomDao extends JpaRepository<Room, Integer> {

	List<Room> findByHostelId(Long hostelId);

}
