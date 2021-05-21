package com.cg.testAllotment;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.IAllotmentDao;
import com.cg.dao.IRoomDao;
import com.cg.dao.IStudentDao;
import com.cg.dto.AllotmentDto;
import com.cg.dto.RoomDTO;
import com.cg.dto.StudentDTO;
import com.cg.entities.Allotment;
import com.cg.entities.Room;
import com.cg.entities.Student;
import com.cg.services.AllotmentServiceImpl;
import com.cg.services.IAllotmentService;



@SpringBootTest
public class TestAddAllotment {
	

	@InjectMocks
	private IAllotmentService allotmentService=new AllotmentServiceImpl();
	
	@Mock
	private IAllotmentDao allotmentDao;
	
	@Mock
	private IRoomDao roomdao;
	
	@Mock
	private IStudentDao studentDao;
	
	Logger logger = LoggerFactory.getLogger(TestAddAllotment.class);
	AllotmentDto allotmentdto;
	Allotment allotment;
	
	List<Student> liststudent=new ArrayList<>();
	List<Room> listroom=new ArrayList<>();

	
	@BeforeEach
	public void init() {
//		liststudent.add(new Student(1,"Qai","raz@gm.com","04-06-2000"))
		allotmentdto =new AllotmentDto();
		allotment= new Allotment();
		
		
		
		
	}
	@Test
	@DisplayName("test case name")
	public void addAllotmentTest() {
		
	}
	
}
