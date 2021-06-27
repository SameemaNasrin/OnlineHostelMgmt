package com.cg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IHostelDao;
import com.cg.dto.HostelDto;
import com.cg.entities.Hostel;
import com.cg.entities.Student;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.helper.Helper;


/** 
 * @Author - Sameema Nasrin
 * @Version - 1.0
 * Description - This service class contains the service regarding the Hostel
 */
@Service
public class HostelServiceImpl implements IHostelService{

	@Autowired
	IHostelDao hostelDao;
	
	/** 
	   * @Param hostelDto HostelDto
	   * @return hostel id
	   * Description - This method adds new hostel and returns hostel id
	   * createdAt - 18-May-2021
	   */
	public Integer addHostel(HostelDto hostelDto) {
		Hostel hostel = new Hostel();
		
		hostel.setName(hostelDto.getName());
		hostel.setContact(hostelDto.getContact());
		hostel.setType(hostelDto.getType());
		hostel.setAddress(hostelDto.getAddress());
		hostel.setFee(hostelDto.getFee());
		hostel.setImgUrl(hostelDto.getImgUrl());
		
		return hostelDao.save(hostel).getId();
	}
	
	/** 
	   * @return list of hostel
	   * Description - This method finds all hostels and returns list of hostel else throws exception
	   * createdAt - 18-May-2021
	   */
	public List<Hostel> viewAllHostel() throws HostelNotFoundException{
		List<Hostel> list = hostelDao.findAll();
		if (list.isEmpty()) {
			throw new HostelNotFoundException("No Hostel found");
		}
		list.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
		return list;
	}
	
	/** 
	   * @return hostel
	   * Description - This method finds hostel by hostel id and returns the hostel if present else throws exception
	   * createdAt - 18-May-2021
	   */
	public Hostel viewHostelById(Integer hid) throws HostelNotFoundException {
		Optional<Hostel> optHostel = hostelDao.findById(hid);
		if (!optHostel.isPresent()) {
			throw new HostelNotFoundException("No hostel found for id " + hid);
		}
		return optHostel.get();
		
	}

	@Override
	public List<Hostel> getHostelByName(String name) throws HostelNotFoundException {
		List<Hostel> hostel = hostelDao.findByName(name);
		if (hostel.isEmpty())
			throw new HostelNotFoundException("No hostel found with name " + name);
		return hostel;
	}
}
