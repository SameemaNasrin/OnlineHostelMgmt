package com.cg.services;

import java.util.List;

import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

public interface IAllotmentService {

	public int addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException, StudentNotFoundException;
	public Integer removeAllotment(Integer allotmentId,AllotmentDto allotmentDto) throws AllotmentNotFoundException, RoomNotFoundException;
	public List<Allotment> viewAllotmentByHostelId(Long hid) throws RoomNotFoundException, AllotmentNotFoundException;
}
