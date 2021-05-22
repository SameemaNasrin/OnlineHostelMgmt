package com.cg.testAllotment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IFeeStructureDao;
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.dto.AllotmentDto;
import com.cg.dto.StudentDTO;
import com.cg.entities.Allotment;
import com.cg.entities.FeeStructure;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.exceptions.GenderTypeMismatchException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.helper.Helper;
import com.cg.services.AllotmentServiceImpl;
import com.cg.services.IAllotmentService;

@SpringBootTest
public class TestAddAllotment {
	

	@InjectMocks
	private IAllotmentService service=new AllotmentServiceImpl();
	
	@Mock
	private IAllotmentDao allotmentDao;
	
	@Mock
	private IRoomDao roomDao;
	
	@Mock
	private IFeeStructureDao feestructureDao;
	
	@Mock
	private IHostelDao hostelDao;
	
	@Mock
	private IStudentDao studentDao;

	@BeforeEach
	public void beforeEach() {
		Hostel hostel1=new Hostel();
		hostel1.setId(5001);
		hostel1.setType(Helper.BOYS);
		Student student1=new Student();
		student1.setGender(Helper.MALE);
		Student student2=new Student();
		student2.setGender(Helper.FEMALE);
		Allotment allotment1=new Allotment();
		allotment1.setId(1);
		Room room1=new Room();
		room1.setHostel(hostel1);
		List<Allotment> lstallotment=new ArrayList<>();
		lstallotment.add(allotment1);
		
		when(studentDao.findById(101)).thenReturn(Optional.of(student1));
		when(studentDao.findById(102)).thenReturn(Optional.empty());
		when(studentDao.findById(103)).thenReturn(Optional.of(student2));
		when(roomDao.findById(1)).thenReturn(Optional.of(room1));
		when(roomDao.findById(2)).thenReturn(Optional.empty());
		when(allotmentDao.findByHostelId(5001)).thenReturn(lstallotment);
		when(allotmentDao.findByHostelId(5002)).thenReturn(new ArrayList<>());
		when(roomDao.save(any(Room.class))).thenReturn(new Room());
		Allotment allotment=new Allotment();
		allotment.setId(1001);
		when(allotmentDao.save(any(Allotment.class))).thenReturn(allotment);
		when(feestructureDao.save(any(FeeStructure.class))).thenReturn(new FeeStructure());
		
		
	}
	@Test
	@DisplayName("Test for add allotment1")
	public void addAllotmentTest() throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException{
		AllotmentDto allotmentdto=new AllotmentDto();
		allotmentdto.setStudentId(101);
		allotmentdto.setHostelId(5001);
		allotmentdto.setRoomId(1);
		assertNotNull(service.addAllotment(allotmentdto));
	}
	
	@Test
	@DisplayName("Test for add allotment2")
	public void addAllotmentTest2() throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException{
		AllotmentDto allotmentdto=new AllotmentDto();
		allotmentdto.setStudentId(102);
		allotmentdto.setHostelId(5001);
		allotmentdto.setRoomId(1);
		assertThrows(StudentNotFoundException.class,()->service.addAllotment(allotmentdto));
	}
	@Test
	@DisplayName("Test for add allotment3")
	public void addAllotmentTest3() throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException{
		AllotmentDto allotmentdto=new AllotmentDto();
		allotmentdto.setStudentId(103);
		allotmentdto.setHostelId(5001);
		allotmentdto.setRoomId(1);
		assertThrows(GenderTypeMismatchException.class,()->service.addAllotment(allotmentdto));
	}
	@Test
	@DisplayName("Test for add allotment4")
	public void addAllotmentTest4() throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException{
		AllotmentDto allotmentdto=new AllotmentDto();
		allotmentdto.setStudentId(101);
		allotmentdto.setHostelId(5001);
		allotmentdto.setRoomId(2);
		assertThrows(RoomNotFoundException.class,()->service.addAllotment(allotmentdto));
	}
}
