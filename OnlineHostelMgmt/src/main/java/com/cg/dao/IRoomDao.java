package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Room;

public interface IRoomDao extends JpaRepository<Room, Integer> {

}
