 package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IHostelDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.WardenDto;
import com.cg.entities.Hostel;
import com.cg.entities.Warden;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.WardenNotFoundException;

@Service
public class WardenServiceImpl implements IWardenService {
	
	@Autowired
	IWardenDao wardenDao;
	
	@Autowired
	IHostelDao hostelDao;

	@Override
	public Integer addWarden(WardenDto wardenDto) throws HostelNotFoundException {
		Warden warden = new Warden();
		warden.setName(wardenDto.getName());
		warden.setEmail(wardenDto.getEmail());
		warden.setId(wardenDto.getId());
		
		
		Hostel hostel = hostelDao.findById(wardenDto.getHostelId()).orElseThrow(
				() -> new HostelNotFoundException("Hostel not found with id " + wardenDto.getHostelId()));
		
		warden.setHostel(hostel);
		return wardenDao.save(warden).getId();
		
	}

	@Override
	public List<Warden> viewAllWarden() throws WardenNotFoundException{
		List<Warden> list = wardenDao.findAll();
		if(list.isEmpty()) {
			throw new WardenNotFoundException("No Warden Found");
		}
		list.sort((w1,w2) -> w1.getName().compareTo(w2.getName()));
		return list;
	}

	@Override
	public Warden viewWardenByWardenId(Integer wardenId) throws WardenNotFoundException  {
		return wardenDao.findById(wardenId).orElseThrow(() -> new WardenNotFoundException("No Warden found for id: "+ wardenId));
	}

	@Override
	public List<Warden> viewWardenByHostelId(Long hostelId) throws WardenNotFoundException {
		List<Warden> wardens = wardenDao.findByHostelId(hostelId);
		
		if(wardens.isEmpty())
			throw new WardenNotFoundException("No warden found for Hostel Id: " + hostelId);
		return wardens;
	}
	

}
