 package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IWardenDao;
import com.cg.dto.WardenDto;
import com.cg.entities.Warden;
import com.cg.exceptions.WardenNotFoundException;

@Service
public class WardenServiceImpl implements IWardenService {
	
	@Autowired
	IWardenDao wardenDao;

	@Override
	public Integer addWarden(WardenDto wardenDto) {
		Warden warden = new Warden();
		warden.setName(wardenDto.getName());
		warden.setEmail(wardenDto.getEmail());
		warden.setId(wardenDto.getId());
		warden.setHostel(wardenDto.getHostel());
		return wardenDao.save(warden).getId();
		
	}

	@Override
	public List<Warden> viewAllWarden() throws WardenNotFoundException {
		List<Warden> list=wardenDao.findAll();
		if(list.isEmpty()) {
			throw new WardenNotFoundException("Sorry No Warden Found");
		}
		list.sort((h1,h2) -> h1.getName().compareTo(h2.getName()));
		return list;
	}

	@Override
	public Warden viewWardenByWId(Integer wid) throws WardenNotFoundException  {
		Optional<Warden> optWarden = wardenDao.findById(wid);
		if(!optWarden.isPresent()) {
			throw new WardenNotFoundException("No Warden found for id:"+ wid);
		}
		return optWarden.get();
	}

	@Override
	public Warden viewWardenByHId(Long hid) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
