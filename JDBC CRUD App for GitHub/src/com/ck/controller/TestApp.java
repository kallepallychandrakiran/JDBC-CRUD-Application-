package com.ck.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.ck.dto.Student;
import com.ck.service.IStudentService;
import com.ck.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("WELCOME TO JDBC CRUD APPLICATION");
			System.out.println("1, CREATE");
			System.out.println("2, READ");
			System.out.println("3, UPDATE");
			System.out.println("4, DELETE");
			System.out.println("5, EXIT");
			System.out.println("Please Enter your choice " + "to perform operations on the database");
			String option = br.readLine();
			switch (option) {
			case "1":
				insertoperation();
				break;
			case "2":
				selectoperation();
				break;
			case "3":
				updateoperation();
				break;
			case "4":
				deleteoperation();
				break;
			case "5":
				System.out.println("Thanks for using application! Secured exit from console");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option please try again with valid option....!");

			}

		}

	}

	private static void deleteoperation() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please Enter the StudentID which needs to be deleted");
		int sid = scanner.nextInt();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		String status = studentService.deleteStudent(sid);
		if(status.equalsIgnoreCase("success")){
			System.out.println("record deletion successfully done!");
		} else {
			System.out.println("Record deletion unsuccesfull");
		}

	}

	private static void updateoperation() throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter which record needs to be updated");
		String sid = br.readLine();
		
		IStudentService studentService  =  StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(Integer.parseInt(sid));
		
		if(student!=null) {
			Student newStudent = new Student();
			System.out.println("Student id is ::  "+student.getSid());
			newStudent.setSid(student.getSid());
			
		System.out.println("Student oldName is "+student.getSname()+"Please enter newname");
		String newname = br.readLine();
		if(newname.equals("") || newname=="") {
			newStudent.setSname(student.getSname());
		} else {
			newStudent.setSname(newname);
		}
		
		//Age
		
		System.out.println("Student oldAgeis "+student.getSage()+"Please enter newage");
		String newage = br.readLine();
		if(newage.equals("") || newage =="") {
			newStudent.setSage(student.getSage());
		} else {
			newStudent.setSage(Integer.parseInt(newage));
		}
		
		//Address
		System.out.println("Student oldAddress is "+student.getSaddress()+"Please enter newaddress");
		String newaddress = br.readLine();
		if(newaddress.equals("") || newaddress =="") {
			newStudent.setSaddress(student.getSaddress());
		} else {
			newStudent.setSaddress(newaddress);
		}
		
		}
	}

	private static void selectoperation() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter The id of the Student which needs to be searched");
		int sid = scanner.nextInt();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student std = studentService.searchStudent(sid);
		if (std != null) {
			System.out.println(std.toString());
		} else {
			System.out.println("Record not found for the given sid " + sid);

		}
	}

	private static void insertoperation() {

		IStudentService studentService = StudentServiceFactory.getStudentService();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the student name");
		String sname = scanner.next();
		System.out.println("Enter the student age");
		int sage = scanner.nextInt();
		System.out.print("Enter the student addres :: ");
		String saddress = scanner.next();

		String msg = studentService.addStudent(sname, sage, saddress);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Record Insertion Sucessfully done");
		} else {
			System.out.println("record insertion failed...Please try again");

		}

		scanner.close();
	}
}
