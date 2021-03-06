package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.entities.Allotment;
import com.cg.entities.Room;

@Repository
public interface IAllotmentDao extends JpaRepository<Allotment, Integer> {

	public List<Allotment> findByRoom(Room room);
	
	@Query("from Allotment a inner join a.room r inner join a.hostel h where h.id=hostel_id")
	public List<Allotment> findByHostelId(@Param("hostel_id") Integer hostelId);
}
