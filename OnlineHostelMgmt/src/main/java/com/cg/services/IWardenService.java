package com.cg.services;

import java.util.List;

import com.cg.dto.WardenDto;
import com.cg.entities.Warden;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.WardenNotFoundException;

public interface IWardenService {

	public Integer addWarden(WardenDto wardendto) throws HostelNotFoundException;

	public List<Warden> viewAllWarden() throws WardenNotFoundException;

	public Warden viewWardenByWardenId(Integer wid) throws WardenNotFoundException;

	public List<Warden> viewWardenByHostelId(Integer hostelid) throws WardenNotFoundException, HostelNotFoundException;

}
