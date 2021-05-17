package com.cg.services;

import java.util.List;

import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;

public interface IAllotmentService {

	public int addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException;
	public Integer removeAllotment(Integer allotmentId) throws AllotmentNotFoundException;
	public List<Allotment> viewAllotmentByHostelId(Long hid) throws HostelNotFoundException, AllotmentNotFoundException;
}
