package com.cg.testAllotment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
import com.cg.dao.IHostelDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.entities.Allotment;
import com.cg.entities.Hostel;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.exceptions.AllotmentNotFoundException;
import com.cg.exceptions.HostelNotFoundException;
import com.cg.exceptions.RoomNotFoundException;
import com.cg.services.AllotmentServiceImpl;
import com.cg.services.IAllotmentService;
//NotWorking
@SpringBootTest
public class TestViewAllotmentByHostelId {
	@InjectMocks
	private IAllotmentService service=new AllotmentServiceImpl();
	@Mock
	private IAllotmentDao allotmentDao;
	@Mock
	private IRoomDao roomDao;
	@Mock
	private IHostelDao hostelDao;
	@Mock
	private IStudentDao studentDao;
	
	Student student2;
	Student student3;
	
	@BeforeEach
	public void beforeEach()throws RoomNotFoundException,AllotmentNotFoundException,
	HostelNotFoundException{
		List<Allotment> list1=new ArrayList<>();
		List<Allotment> list2=new ArrayList<>();
	
		student2.setId(101);
		student2.setName("Stu 1");
		student2.setEmail("ab@dm.com");
		student2.setDob(LocalDate.of(2001, 07, 02));
		student2.setGender("male");
		student2.setGuardianName("Parent 1");
		student2.setMobile("98546321456");
		student2.setAddress("kolkata");
		Optional<Student> student1=Optional.of(student2);
		student3.setId(101);
		student3.setName("Stu 2");
		student3.setEmail("abcd@dm.com");
		student3.setDob(LocalDate.of(2000, 05, 12));
		student3.setGender("male");
		student3.setGuardianName("Parent 2");
		student3.setMobile("98546324569");
		student3.setAddress("sodepur");
		Optional<Student> student2=Optional.of(student3);
		Optional<Hostel> hostel1=Optional.of(new Hostel(1, "GNIT Hostel1", "9898172634", "boys", "sodepur, west bengal", 30000.0, null));
	//	Optional<Hostel> hostel2=Optional.empty();
		Optional<Hostel> hostel3=Optional.of(new Hostel(3, "GNIT Hostel3", "9878992634", "boys", "sodepur, west bengal", 39000.0, null));
		Optional<Hostel> hostel4=Optional.of(new Hostel(4, "GNIT Hostel4", "9845642634", "girls", "sodepur, west bengal", 35000.0, null));
		Optional<Room> room1=Optional.of(new Room(51,"12A" , hostel1.get(), "empty", 2, 4));
	//	Optional<Room> room2=Optional.empty();
		Optional<Room> room3=Optional.of(new Room(52,"12B" , hostel4.get(), "empty", 2, 3));
		
		list1.add(new Allotment(1001,room1.get(),student1.get(),hostel1.get()));
		list1.add(new Allotment(1002,room1.get(),student2.get(),hostel1.get()));
		when(allotmentDao.findByHostelId(hostel3.get())).thenReturn(list2);
		when(allotmentDao.findByHostelId(hostel1.get())).thenReturn(list1);
		
	
	}
	
	@Test
	@DisplayName("Test Case for viewing Allotment for hostelId 1")
	public void viewAllotmentTest() throws RoomNotFoundException,AllotmentNotFoundException,
	HostelNotFoundException{
		assertTrue(service.viewAllotmentByHostelId(1).size()>0);
		
	}
	@Test
	@DisplayName("Negative Test Case for viewing Allotment for hostelId 5")
	public void viewAllotmentTest1() throws RoomNotFoundException,AllotmentNotFoundException,
	HostelNotFoundException{
		assertThrows(HostelNotFoundException.class,()->service.viewAllotmentByHostelId(2));
	}
	@Test
	@DisplayName("Negative Test Case for viewing Allotment for no room registered")
	public void viewAllotmentTest2() throws RoomNotFoundException,AllotmentNotFoundException,
	HostelNotFoundException{
		assertThrows(RoomNotFoundException.class,()->service.viewAllotmentByHostelId(3));
	}
	@Test
	@DisplayName("Negative Test Case for viewing Allotment for no allotment registered")
	public void viewAllotmentTest3() throws RoomNotFoundException,AllotmentNotFoundException,
	HostelNotFoundException{
		assertThrows(AllotmentNotFoundException.class,()->service.viewAllotmentByHostelId(4));
	}

}
