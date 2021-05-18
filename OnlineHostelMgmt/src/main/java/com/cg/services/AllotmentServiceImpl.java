package com.cg.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.AllotmentDto;
import com.cg.dto.RoomDTO;
import com.cg.entities.Allotment;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.entities.Warden;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.exceptions.WardenNotFoundException;

@Service
public class AllotmentServiceImpl implements IAllotmentService{

	@Autowired
	IAllotmentDao allotmentDao;
	
	@Autowired
	IHostelDao hostelDao;// why do we need it?
	
	@Autowired
	IStudentDao studentDao;
	
	@Autowired
	IRoomDao roomDao;
	
	@Override
	@Transactional
	public int addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException, StudentNotFoundException {
		Allotment allotment=new Allotment();
		
		Room room = roomDao.findById(allotmentDto.getRoomId()).orElseThrow(() -> new RoomNotFoundException("Room Doesnot exist with Id " + allotmentDto.getRoomId()));
		Student student = studentDao.findById(allotmentDto.getStudentId()).orElseThrow(() -> new StudentNotFoundException("Student not found with id " + allotmentDto.getStudentId()));
	
		allotment.setRoom(room);
		allotment.setStudent(student);
		
		return allotmentDao.save(allotment).getId();
		
	}

	@Override
	public Integer removeAllotment(Integer aid) throws AllotmentNotFoundException{
		//Allotment allotment = allotmentDao.findById(aid).orElseThrow(()-> new AllotmentNotFoundException("No Allotment found for id:"+ aid));
		
		//check this logic 
		if(!allotmentDao.findById(aid).isPresent())
			throw new AllotmentNotFoundException("No Allotment found for id:"+ aid);
		allotmentDao.deleteById(aid);
		
		return aid;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(Long hostelId) throws RoomNotFoundException, AllotmentNotFoundException{
//		Hostel hostel = hosteldao.findById(hostelId).orElseThrow(()->new HostelNotFoundException("Hostel not found"));
//		List<Allotment> allotments=allotmentDao.filter(d->d.hostelId=hid).stream().collect(Collectors.toList());
//		if(allotments.isEmpty())
//			throw new AllotmentNotFoundException("Allotment not found");
//		return allotments;
		
		//check this logic
		List<Room> rooms = roomDao.findByHostelId(hostelId);
		
		if(rooms.isEmpty()) {
			throw new RoomNotFoundException("No room found for hostel id " + hostelId);
		}
		
		List<Allotment> allotment = null;
		
		for(Room room : rooms) {
			List<Allotment> a = allotmentDao.findByRoomId(room.getRoomId());
			if(a.isEmpty())
				throw new AllotmentNotFoundException("Allotment not found for room id " + room.getRoomId());
			allotment.addAll(a);
		}
		
		return allotment;
	}
}