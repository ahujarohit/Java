package com.vedisoft.jdbc.daos;

import com.vedisoft.jdbc.utilities.MyDatabaseConnection;
import com.vedisoft.jdbc.pojos.*;

import java.sql.*;
import java.util.*;

public class CourseDao {

	public boolean createCourse(Course course) {
		Connection conn = MyDatabaseConnection.getConnection();
		try {
			String sql = "insert into courses (coursename, " + "coursetype) values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseType());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to create a new row." + sq);
			return false;
		}
		return true;
	}

	public boolean editCourse(Course course) {
		Connection conn = MyDatabaseConnection.getConnection();
		try {
			String sql = "update courses set coursename = ?, " + "coursetype = ? where courseid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseType());
			ps.setInt(3, course.getCourseId());
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to update row." + sq);
			return false;
		}
		return true;
	}

	public boolean removeCourse(int courseId) {
		Connection conn = MyDatabaseConnection.getConnection();
		try {
			String sql = "delete from courses where courseid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			ps.executeUpdate();
		} catch (SQLException sq) {
			System.out.println("Unable to delete row." + sq);
			return false;
		}
		return true;
	}

	public Course find(int courseId) {
		Connection conn = MyDatabaseConnection.getConnection();
		Course course = new Course();
		try {
			String sql = "select * from courses where courseid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				course.setCourseId(courseId);
				course.setCourseName(rs.getString("coursename"));
				course.setCourseType(rs.getString("coursetype"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return course;
	}

	public ArrayList<Course> findAll() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Course> listCourses = new ArrayList<Course>();
		try {
			String sql = "select * from courses";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseType(rs.getString("coursetype"));
				listCourses.add(course);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows.");
		}
		return listCourses;
	}

	public ArrayList<Integer> findAllIds() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Integer> listIds = new ArrayList<Integer>();
		try {
			String sql = "select courseId from courses";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listIds.add(rs.getInt("courseId"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows.");
		}
		return listIds;
	}

	public ArrayList<String> findAllCourses() {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<String> listCourses = new ArrayList<String>();
		try {
			String sql = "select coursename from courses";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listCourses.add(rs.getString("coursename"));
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows.");
		}
		return listCourses;
	}

	public ArrayList<Course> findAllByCourseType(String courseType) {
		Connection conn = MyDatabaseConnection.getConnection();
		ArrayList<Course> listCourses = new ArrayList<Course>();
		try {
			String sql = "select * from courses where coursetype = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, courseType);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseType(rs.getString("coursetype"));
				listCourses.add(course);
			}
		} catch (SQLException sq) {
			System.out.println("Unable to fetch rows." + sq);
		}
		return listCourses;
	}

	public static void main(String args[]) {

		// Course course = new Course("Java","Certificate");
		// CourseDao courseDao = new CourseDao();
		// courseDao.createCourse(course);

		// Course course = new Course(8,".NET","Diploma");
		// CourseDao courseDao = new CourseDao();
		// courseDao.editCourse(course);

		// CourseDao courseDao = new CourseDao();
		// courseDao.removeCourse(8);

		// CourseDao courseDao = new CourseDao();
		// ArrayList<Course> al = courseDao.findAll();
		// for(Course course : al) {
		// System.out.println(course);
		// }

		// CourseDao courseDao = new CourseDao();
		// ArrayList<Course> al = courseDao.findAllByCourseType("certificate");
		// for(Course course : al) {
		// System.out.println(course);
		// }

		// CourseDao courseDao = new CourseDao();
		// Course course = courseDao.find(1);
		// System.out.println(course);

	}
}
