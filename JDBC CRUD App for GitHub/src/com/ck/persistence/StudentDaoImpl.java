package com.ck.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ck.dto.Student;
import com.ck.util.JdbcUtil;

//Persistence logic using JDBC API

public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultset = null;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {

		String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddress`) values(?,?,?)";
		try {
			connection = JdbcUtil.getJdbcconnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1)
					return "success";
			}

		} catch (SQLException | IOException e) {

			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {

		String sqlSearchQuery = "select sid,sname,sage,saddress from student where sid=?";
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcconnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSearchQuery);
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				resultset = pstmt.executeQuery(sqlSearchQuery);
			}

			if (resultset != null) {
				if (resultset.next()) {
					student = new Student();
					student.setSid(resultset.getInt(1));
					student.setSname(resultset.getString(2));
					student.setSage(resultset.getInt(3));
					student.setSaddress(resultset.getString(4));

					return student;
				}
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@SuppressWarnings("unused")
	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "delete from student where sid =?";

		try {
			connection = JdbcUtil.getJdbcconnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				
				int rowAffected = pstmt.executeUpdate();
				if(rowAffected==1) {
					return "success";
				} else {
					return "not found";
				}
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public String updateStudent(Student student) {
		String sqlUpdateQuery = "update student set sname =?,sage=?,saddress=? where sid=?";
		try {
			connection = JdbcUtil.getJdbcconnection();
			if(connection!=null)
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			if(pstmt!=null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(1, student.getSage());
				pstmt.setString(1, student.getSaddress());
				pstmt.setInt(1, student.getSid());
				
				int rowAffected = pstmt.executeUpdate();
				if(rowAffected==1)
					return "success";
			}
			
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "failure";
	}

}
