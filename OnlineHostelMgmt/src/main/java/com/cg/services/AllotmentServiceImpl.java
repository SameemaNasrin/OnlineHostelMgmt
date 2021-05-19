package com.cg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.helper.Helper;

@Service
public class AllotmentServiceImpl implements IAllotmentService {
	
	Logger logger = LoggerFactory.getLogger(AllotmentServiceImpl.class);

	@Autowired
	IAllotmentDao allotmentDao;

	@Autowired
	IStudentDao studentDao;

	@Autowired
	IRoomDao roomDao;

	@Autowired
	IFeeStructureDao feeStructureDao;

	@Autowired
	IHostelDao hostelDao;

	@Override
	@Transactional
	public Integer addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException, StudentNotFoundException {
		Allotment allotment = new Allotment();
		/*
		 * finding room id and checking for availability of beds if unavailable then
		 * throwing exception
		 */
		Room room = roomDao.findById(allotmentDto.getRoomId()).orElseThrow(
				() -> new RoomNotFoundException("Room does not exist with Id " + allotmentDto.getRoomId()));
		Student student = studentDao.findById(allotmentDto.getStudentId()).orElseThrow(
				() -> new StudentNotFoundException("Student not found with id " + allotmentDto.getStudentId()));
		Integer size = room.getMaximumSize();
		if (size <= 0) {
			throw new RoomNotFoundException("Room not empty");
		} else {
			room.setMaximumSize(size - 1);
		}
		// updating reduced room size in database
		roomDao.save(room);
		// if student id is valid and room has availability
		// making entry in allotment and feeStructure entity classes
		allotment.setRoom(room);
		allotment.setStudent(student);
		Allotment savedAllotment = allotmentDao.save(allotment);
		FeeStructure feeStructure = new FeeStructure();
		feeStructure.setAllotment(allotment);
		feeStructure.setPaymentStatus(Helper.NOT_PAID);
		feeStructure.setStudent(student);
		// why not setting other fee structure parameters ? ? (total fees)
		feeStructure.setTotalFees(allotmentDto.getTotalFees());
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
	public Integer removeAllotment(Integer allotmentId) throws AllotmentNotFoundException, RoomNotFoundException {
		Allotment allotment = allotmentDao.findById(allotmentId)
				.orElseThrow(() -> new AllotmentNotFoundException("No Allotment found for id:" + allotmentId));
		Room room = allotment.getRoom();
		if (room == null) {
			throw new RoomNotFoundException("Room does not exist");
		}

		Integer size = room.getMaximumSize();
		room.setMaximumSize(size + 1);
		roomDao.save(room);
		allotmentDao.deleteById(allotment.getId());
		return allotmentId;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(Long hostelId)
			throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException {
		Hostel hostel = hostelDao.findById(hostelId)
				.orElseThrow(() -> new HostelNotFoundException("No hostel found with id " + hostelId));
		List<Room> rooms = hostel.getRooms().stream().collect(Collectors.toList());

		if (rooms.isEmpty()) {
			throw new RoomNotFoundException("No room data found for hostel id " + hostel.getId());
		}

		List<Allotment> allotments = new ArrayList<>();
		/* have to check this */
		for (Room room : rooms) {
			List<Allotment> a = allotmentDao.findByRoom(room);
			logger.info("hererE");
			logger.info(String.valueOf(a.size()));
			if (a.isEmpty())
				throw new AllotmentNotFoundException("Allotment not found for room id " + room.getRoomId());
			allotments.addAll(a);
		}

		return allotments;
	}
}