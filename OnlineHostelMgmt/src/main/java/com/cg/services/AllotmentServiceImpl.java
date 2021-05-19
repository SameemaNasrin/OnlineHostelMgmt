package com.cg.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.entities.FeeStructure;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;

@Service
public class AllotmentServiceImpl implements IAllotmentService {

	@Autowired
	IAllotmentDao allotmentDao;

	@Autowired
	IStudentDao studentDao;

	@Autowired
	IRoomDao roomDao;

	@Autowired
	IFeeStructureDao feeStructureDao;

	@Override
	@Transactional
	public int addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException, StudentNotFoundException {
		Allotment allotment = new Allotment();
		/*
		 * finding room id and checking for availability of beds if unavailable then
		 * throwing exception
		 */
		Room room = roomDao.findById(allotmentDto.getRoomId())
				.orElseThrow(() -> new RoomNotFoundException("Room Doesnot exist with Id " + allotmentDto.getRoomId()));
		Integer size = room.getMaximumSize();
		if (size <= 0) {
			throw new RoomNotFoundException("Room not empty");
		} else {
			room.setMaximumSize(size - 1);
		}
		Student student = studentDao.findById(allotmentDto.getStudentId()).orElseThrow(
				() -> new StudentNotFoundException("Student not found with id " + allotmentDto.getStudentId()));
		// if student id is valid and room has availability
		// making entry in allotment and feeStructure entity classes
		allotment.setRoom(room);
		allotment.setStudent(student);
		Allotment savedAllotment = allotmentDao.save(allotment);
		FeeStructure feeStructure = new FeeStructure();
		feeStructure.setAllotment(allotment);
		feeStructure.setPaymentStatus("Not Paid");
		feeStructure.setStudent(student);
		feeStructureDao.save(feeStructure);
		// returning allotment id for the allocation
		return savedAllotment.getId();

	}

	/*
	 * to remove an existing allotment of room first find is the allotment id is
	 * present in allotment table, if not found throw respective exception else goto
	 * next step and delete the allotment row and then add 1 to size of the room id
	 */
	@Override
	@Transactional
	public Integer removeAllotment(Integer allotmentId, AllotmentDto allotmentDto)
			throws AllotmentNotFoundException, RoomNotFoundException {
		allotmentDao.findById(allotmentId)
				.orElseThrow(() -> new AllotmentNotFoundException("No Allotment found for id:" + allotmentId));
		Room room = roomDao.findById(allotmentDto.getRoomId())
				.orElseThrow(() -> new RoomNotFoundException("Room Doesnot exist with Id " + allotmentDto.getRoomId()));
		Integer size = room.getMaximumSize();
		room.setMaximumSize(size + 1);
		allotmentDao.deleteById(allotmentId);
		return allotmentId;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(Long hostelId)
			throws RoomNotFoundException, AllotmentNotFoundException {
		List<Room> rooms = roomDao.findByHostelId(hostelId);

		if (rooms.isEmpty()) {
			throw new RoomNotFoundException("No room data found for hostel id " + hostelId);
		}

		List<Allotment> allotment = new ArrayList<>();

		for (Room room : rooms) {
			List<Allotment> a = allotmentDao.findByRoom(room);
			if (a.isEmpty())
				throw new AllotmentNotFoundException("Allotment not found for room id " + room.getRoomId());
			allotment.addAll(a);
		}

		return allotment;
	}
}