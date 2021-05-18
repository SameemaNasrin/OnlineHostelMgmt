package com.cg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.dao.IWardenDao;
import com.cg.dto.AllotmentDto;
import com.cg.dto.RoomDTO;
import com.cg.entities.Allotment;
import com.cg.entities.FeeStructure;
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
	
	@Autowired
	IFeeStructureDao feeStructureDao;
	
	@Override
	@Transactional
	public int addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException, StudentNotFoundException {
		Allotment allotment=new Allotment();
		
		Room room = roomDao.findById(allotmentDto.getRoomId()).orElseThrow(() -> new RoomNotFoundException("Room Doesnot exist with Id " + allotmentDto.getRoomId()));
		 Integer size=room.getMaximumSize();
		 if(size<=0) {
			 throw new RoomNotFoundException("Room not empty");
		 }
		 else {
			 room.setMaximumSize(size-1);
		 }
		Student student = studentDao.findById(allotmentDto.getStudentId()).orElseThrow(() -> new StudentNotFoundException("Student not found with id " + allotmentDto.getStudentId()));
	
		allotment.setRoom(room);
		allotment.setStudent(student);
		Allotment savedAllotment = allotmentDao.save(allotment);
		FeeStructure feeStructure = new FeeStructure();
		feeStructure.setAllotment(allotment);
		feeStructure.setPaymentStatus("Not Paid");
		feeStructure.setStudent(student);
		feeStructureDao.save(feeStructure);
		return savedAllotment.getId();
		
	}

	@Override
	public Integer removeAllotment(Integer allotmentId) throws AllotmentNotFoundException{
		allotmentDao.findById(allotmentId).orElseThrow(()-> new AllotmentNotFoundException("No Allotment found for id:"+ allotmentId));
		
		allotmentDao.deleteById(allotmentId);
		
		return allotmentId;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(Long hostelId) throws RoomNotFoundException, AllotmentNotFoundException{
		List<Room> rooms = roomDao.findByHostelId(hostelId);
		
		if(rooms.isEmpty()) {
			throw new RoomNotFoundException("No room data found for hostel id " + hostelId);
		}
		
		List<Allotment> allotment = new ArrayList<>();
		
		for(Room room : rooms) {
			List<Allotment> a = allotmentDao.findByRoom(room);
			if(a.isEmpty())
				throw new AllotmentNotFoundException("Allotment not found for room id " + room.getRoomId());
			allotment.addAll(a);
		}
		
		return allotment;
	}
}