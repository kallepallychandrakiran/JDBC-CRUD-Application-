package com.ck.servicefactory;

import com.ck.service.IStudentService;
import com.ck.service.StudentServiceImpl;

public class StudentServiceFactory {
	
	private StudentServiceFactory() {}
	
	private static IStudentService stdService = null;
	
	public static IStudentService getStudentService() {
		if(stdService==null) {
			stdService = new StudentServiceImpl();
			
		}
		return stdService;
	}

}
