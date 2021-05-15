package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IHostelDao;
import com.cg.dto.HostelDto;
import com.cg.entities.Hostel;
import com.cg.exceptions.HostelNotFoundException;

@Service
public class HostelServiceImpl implements IHostelService{

	@Autowired
	IHostelDao hostelDao;
	
	public Long addHostel(HostelDto hostelDto) {
		Hostel hostel = new Hostel();
		
		hostel.setName(hostelDto.getName());
		hostel.setContact(hostelDto.getContact());
		hostel.setType(hostelDto.getType());
		hostel.setAddress(hostelDto.getAddress());
		hostel.setFee(hostel.getFee());
		
		return hostelDao.save(hostel).getId();
	}
	
	public List<Hostel> viewAllHostel() throws HostelNotFoundException{
		List<Hostel> list = hostelDao.findAll();
		if (list.isEmpty()) {
			throw new HostelNotFoundException("No Hostel found");
		}
		list.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
		return list;
	}
	
	public Hostel viewHostelById(Long hid) throws HostelNotFoundException {
		Optional<Hostel> optHostel = hostelDao.findById(hid);
		if (!optHostel.isPresent()) {
			throw new HostelNotFoundException("No hostel found for id" + hid);
		}
		return optHostel.get();
		
	}
}
