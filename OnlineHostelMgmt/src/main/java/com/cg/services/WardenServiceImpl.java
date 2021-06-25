package com.cg.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.IHostelDao;
import com.cg.dao.ILoginDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.WardenDto;
import com.cg.entities.Hostel;
import com.cg.entities.Login;
import com.cg.entities.Warden;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.WardenNotFoundException;

@Service
public class WardenServiceImpl implements IWardenService {

	@Autowired
	IWardenDao wardenDao;

	@Autowired
	IHostelDao hostelDao;

	@Autowired
	ILoginDao loginDao;

	@Override
	public Map<String, String> addWarden(WardenDto wardenDto) throws HostelNotFoundException {
		Warden warden = new Warden();
		warden.setName(wardenDto.getName());
		warden.setEmail(wardenDto.getEmail());
		warden.setId(wardenDto.getId());

		Hostel hostel = hostelDao.findById(wardenDto.getHostelId())
				.orElseThrow(() -> new HostelNotFoundException("Hostel not found with id " + wardenDto.getHostelId()));

		warden.setHostel(hostel);
		Warden savedWarden = wardenDao.save(warden);
		String password = savedWarden.getName().substring(0, 3) + "-" + savedWarden.getId();
		String encryptedPassword = LoginServiceImpl.encryptPassword(password);
		Map<String, String> output = new HashMap<>();
		Login login = new Login();
		login.setEmail(warden.getEmail());
		login.setPassword(encryptedPassword);
		login.setRole("warden");
		loginDao.save(login);
		output.put("wardenId", String.valueOf(savedWarden.getId()));
		output.put("password", password);
		return output;

	}

	@Override
	public List<Warden> viewAllWarden() throws WardenNotFoundException {
		List<Warden> list = wardenDao.findAll();
		if (list.isEmpty()) {
			throw new WardenNotFoundException("No Warden Found");
		}
		list.sort((w1, w2) -> w1.getName().compareTo(w2.getName()));
		return list;
	}

	@Override
	public Warden viewWardenByWardenId(Integer wardenId) throws WardenNotFoundException {
		return wardenDao.findById(wardenId)
				.orElseThrow(() -> new WardenNotFoundException("No Warden found for id: " + wardenId));
	}

	@Override
	public List<Warden> viewWardenByHostelId(Integer hostelId) throws WardenNotFoundException, HostelNotFoundException {
		Hostel hostel = hostelDao.findById(hostelId)
				.orElseThrow(() -> new HostelNotFoundException("No hostel found with id " + hostelId));
		List<Warden> wardens = wardenDao.findByHostelId(hostel.getId());

		if (wardens.isEmpty())
			throw new WardenNotFoundException("No warden found for Hostel Id: " + hostel.getId());
		return wardens;
	}

}
