package com.cg.services;

import java.util.List;

import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.GenderTypeMismatchException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.HostelRoomMismatchException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

public interface IAllotmentService {

	public Integer addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException, HostelNotFoundException, HostelRoomMismatchException;

	public Integer removeAllotment(Integer allotmentId) throws AllotmentNotFoundException, RoomNotFoundException;

	public List<Allotment> viewAllotmentByHostelId(Integer hid)
			throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException;
}
