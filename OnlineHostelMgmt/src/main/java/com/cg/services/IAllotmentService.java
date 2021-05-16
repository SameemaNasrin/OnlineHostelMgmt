package com.cg.services;

import java.util.List;

import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.exceptions.HostelNotFoundException;

public interface IAllotmentService {

	public int addAllotment(AllotmentDto allotmentDto);
	public Boolean removeAllotment(Integer allotmentId);
	public List<Allotment> viewAllotmentByHostelId(int hid) throws HostelNotFoundException;
}
