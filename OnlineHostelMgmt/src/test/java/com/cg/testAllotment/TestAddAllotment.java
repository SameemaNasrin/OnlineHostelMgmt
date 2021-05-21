package com.cg.testAllotment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.dto.AllotmentDto;
import com.cg.entities.Allotment;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.exceptions.GenderTypeMismatchException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.exceptions.StudentNotFoundException;
import com.cg.services.AllotmentServiceImpl;
import com.cg.services.IAllotmentService;


//not working
@SpringBootTest
public class TestAddAllotment {
	

	@InjectMocks
	private IAllotmentService service=new AllotmentServiceImpl();
	
	@Mock
	private IAllotmentDao allotmentDao;
	
	@Mock
	private IRoomDao roomdao;
	
	@Mock
	private IStudentDao studentDao;
	

	AllotmentDto allotmentDto;
	Allotment allotment;

	List<Student> liststudent=new ArrayList<>();
	List<Room> listroom=new ArrayList<>();
	List<Allotment> listallotment=new ArrayList<>();
	List<Hostel> listhostel=new ArrayList<>();
	
	@BeforeEach
	public void init() {
	allotmentDto =new AllotmentDto();
	allotment= new Allotment();
		Student s1=new Student();
		s1.setId(101);
		s1.setName("Student A");
		s1.setEmail("abc@gmail.com");
		s1.setDob(LocalDate.of(2001, 07, 02));
		s1.setGender("male");
		s1.setGuardianName("Parent A");
		s1.setMobile("9865471236");
		s1.setAddress("kolkata");
		liststudent.add(s1);
		listhostel.add(new Hostel(2, "GNIT Hostel", "9898172634", "boys", "sodepur, west bengal", 30000.0, null));
		listroom.add(new Room(51,"12A" , listhostel.get(0), "empty", 2, 4));
	listallotment.add(new Allotment(1001,listroom.get(0),liststudent.get(0),listhostel.get(0)));	

	}
	@Test
	@DisplayName("Test for add allotment")
	public void addAllotmentTest() throws RoomNotFoundException, StudentNotFoundException, GenderTypeMismatchException{
		when(studentDao.findAll()).thenReturn(liststudent);
		when(roomdao.findAll()).thenReturn(listroom);
		assertEquals(1001, service.addAllotment(allotmentDto));
	}

	
}
