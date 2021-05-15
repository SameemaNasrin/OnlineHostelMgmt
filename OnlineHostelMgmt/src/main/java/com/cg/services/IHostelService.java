package com.cg.services;

import java.util.List;

import com.cg.dto.HostelDto;
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;

public interface IHostelService{

	public Long addHostel(HostelDto hostelDto);
	public List<Hostel> viewAllHostel() throws HostelNotFoundException;
	public Hostel viewHostelById(Long hid) throws HostelNotFoundException;
}
