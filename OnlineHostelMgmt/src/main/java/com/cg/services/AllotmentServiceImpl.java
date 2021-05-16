package com.cg.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.exceptions.HostelNotFoundException;

@Service
public class AllotmentServiceImpl implements IAllotmentService{

	@Override
	public int addAllotment(AllotmentDto allotmentDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean removeAllotment(Integer allotmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(int hid) throws HostelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
