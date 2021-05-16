package com.cg.services;

import java.util.List;

import com.cg.dto.WardenDto;
import com.cg.entities.Warden;
import com.cg.exceptions.WardenNotFoundException;

public interface IWardenService {
	
	public Integer addWarden(WardenDto wardendto);
	public List<Warden> viewAllWarden()throws WardenNotFoundException;
	public Warden viewWardenByWId(Integer wid)throws WardenNotFoundException;
	public Warden viewWardenByHId(Long hid);
	
	

}
