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
import com.cg.exceptions.WardenNotFoundException;

@Service
public class AllotmentServiceImpl implements IAllotmentService{

	@Autowired
	IAllotmentDao allotmentDao;
	
	@Autowired
	IHostelDao hosteldao;
	
	@Override
	@Transactional
	public int addAllotment(AllotmentDto allotmentDto) throws RoomNotFoundException {
		Allotment allotment=new Allotment();
	//	allotment.setStudentId(allotmentDto.getStudentId());
		
		Room room=null;
		if(room.getMaximumSize()>0) {
		allotment.setRoomId(room.getRoomId());
		room.setMaximumSize(room.getMaximumSize()-1);
		}
		else
		{
			throw new RoomNotFoundException("room full!!");
		}
		Student student=null;
		/*

	public Integer addEmployee(EmpDto empdto) throws DeptException {
		Emp emp = new Emp();
		emp.setEmpName(empdto.getEmpName());
		emp.setEmpSal(empdto.getEmpSal());
		emp.setEmpDoj(empdto.getDoj());
		Dept dept = null;

		dept = deptdao.findByDeptName(empdto.getDeptName());
		if (dept == null) {
			throw new DeptException("No department found");
		}
		emp.setDept(dept);
		Emp persistedEmp = empdao.save(emp);
		return persistedEmp.getEmpId();
	}
		 */
		
		return 0;
	}

	@Override
	public Integer removeAllotment(Integer aid) throws AllotmentNotFoundException{
		Optional<Allotment> optAllotment = allotmentDao.findById(aid);
		if(!optAllotment.isPresent()) {
			throw new AllotmentNotFoundException("No Allotment found for id:"+ aid);
		}
		return aid;
	}

	@Override
	public List<Allotment> viewAllotmentByHostelId(Long hid) throws HostelNotFoundException, AllotmentNotFoundException {
		Hostel hostel=hosteldao.findById(hid).orElseThrow(()->new HostelNotFoundException("Hostel not found"));
		List<Allotment> allotments=allotmentDao.filter(d->d.hostelId=hid).stream().collect(Collectors.toList());
		if(allotments.isEmpty())
			throw new AllotmentNotFoundException("Allotment not found");
		return allotments;
		
}
}