package com.ck.service;

import com.ck.daofactory.StudentDaoFactory;
import com.ck.dto.Student;
import com.ck.persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao stdDao;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		stdDao = StudentDaoFactory.getStudentdao();
		return stdDao.addStudent(sname, sage, saddress);

	}

	@Override
	public Student searchStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentdao();
		return stdDao.searchStudent(sid);
	}

	@Override
	public String deleteStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentdao();
		return stdDao.deleteStudent(sid);
	}

	@Override
	public String updateStudent(Student student) {
		stdDao = StudentDaoFactory.getStudentdao();
		return stdDao.updateStudent(student);
	}

}
