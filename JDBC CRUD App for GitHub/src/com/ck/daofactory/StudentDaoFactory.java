package com.ck.daofactory;

import com.ck.persistence.IStudentDao;
import com.ck.persistence.StudentDaoImpl;

public class StudentDaoFactory {
	
	private StudentDaoFactory() {}
	
	private static IStudentDao studentDao = null;
	
	public static IStudentDao getStudentdao() {
		if (studentDao == null) {
			studentDao = new StudentDaoImpl();
		}
		return studentDao;
		
	}

}
