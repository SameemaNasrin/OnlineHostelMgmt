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
import com.cg.exceptions.GenderTypeMismatchException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.HostelRoomMismatchException;
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
	public Integer addAllotment(AllotmentDto allotmentDto)
			throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException, HostelNotFoundException, HostelRoomMismatchException {
		Allotment allotment = new Allotment();
		/*
		 * finding room id and checking for availability of beds if unavailable then
		 * throwing exception
		 */

		Room room = roomDao.findById(allotmentDto.getRoomId()).orElseThrow(
				() -> new RoomNotFoundException("Room does not exist with Id " + allotmentDto.getRoomId()));
		Student student = studentDao.findById(allotmentDto.getStudentId()).orElseThrow(
				() -> new StudentNotFoundException("Student not found with id " + allotmentDto.getStudentId()));
		Hostel hostel = hostelDao.findById(allotmentDto.getHostelId()).orElseThrow(
				() -> new HostelNotFoundException("Hostel not found with id " + allotmentDto.getHostelId()));
		
		if(room.getHostel().getId() != hostel.getId())
			throw new HostelRoomMismatchException("No room with id "+ room.getRoomId() + " is present in hostel with hostel id " + hostel.getId());

		// check whether the hostel type and gender matches
		String studentGender = student.getGender();
		String hostelType = room.getHostel().getType();

		if ((studentGender.equalsIgnoreCase(Helper.FEMALE) && hostelType.equalsIgnoreCase(Helper.GIRLS))
				|| (studentGender.equalsIgnoreCase(Helper.MALE) && hostelType.equalsIgnoreCase(Helper.BOYS))
				|| (studentGender.equalsIgnoreCase(Helper.OTHER) && hostelType.equalsIgnoreCase(Helper.OTHERS))) {
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
			allotment.setHostel(hostel);
			Allotment savedAllotment = allotmentDao.save(allotment);
			FeeStructure feeStructure = new FeeStructure();
			feeStructure.setAllotment(allotment);
			feeStructure.setPaymentStatus(Helper.NOT_PAID);
			feeStructure.setStudent(student);

			feeStructure.setTotalFees(allotment.getRoom().getHostel().getFee());
			feeStructureDao.save(feeStructure);
			// returning allotment id for the allocation
			return savedAllotment.getId();

		} else
			throw new GenderTypeMismatchException("Student's gender and hostel type doesn't match");

	}

	/*
	 * to remove an existing allotment of room first find is the allotment id is
	 * present in allotment table, if not found throw respective exception else goto
	 * next step and delete the allotment row and then add 1 to size of the room id
	 */

	// to delete data from allotment table first need to derefer from fee structure
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
		
		FeeStructure feeStructure = feeStructureDao.findByAllotment(allotment);
		if (feeStructure != null) {
			feeStructure.setAllotment(null);
		}

		allotmentDao.deleteById(allotment.getId());
		return allotmentId;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(Integer hostelId)
			throws RoomNotFoundException, AllotmentNotFoundException, HostelNotFoundException {
		Hostel hostel = hostelDao.findById(hostelId)
				.orElseThrow(() -> new HostelNotFoundException("No hostel found with id " + hostelId));
		List<Room> rooms = hostel.getRooms().stream().collect(Collectors.toList());

		if (rooms.isEmpty()) {
			throw new RoomNotFoundException("No room data found for hostel id " + hostel.getId());
		}

		List<Allotment> allotments = new ArrayList<>();
		for (Room room : rooms) {
			List<Allotment> a = allotmentDao.findByRoom(room);
			allotments.addAll(a);
		}

		if (allotments.isEmpty())
			throw new AllotmentNotFoundException("No allotment found for hostel id " + hostel.getId());

		return allotments;
	}
}