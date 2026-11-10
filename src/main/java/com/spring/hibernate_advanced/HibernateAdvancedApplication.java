package com.spring.hibernate_advanced;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_advanced.dao.AppDao;
import com.spring.hibernate_advanced.entity.Course;
import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;
import com.spring.hibernate_advanced.entity.Review;
import com.spring.hibernate_advanced.entity.Student;

@SpringBootApplication
public class HibernateAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateAdvancedApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(AppDao app) {
		return runner -> {
			// createInstructor(app);
			// findInstructor(app);
			// deleteInstructor(app);
			// findInstructorDetail(app);
			// deleteInstructorDetail(app);
			// createInstructorWithCourses(app);
			// findInstructorWithCourse(app);
			// findCoursesForInstructor(app);
			// findInstructorWithCoursesJoinFetch(app);
			// updateInstructor(app);
			// deleteInstructorOnly(app);
			// createCourseAndReview(app);
			// retrieveCourseAndReview(app);
			// deleteCourseAndReviews(app);
			// createCourseAndStudents(app);
			// findCoursesAndStudents(app);
			// addMoreCoursesForStudent(app);
			// deleteCourseById(app);
			deleteStudent(app);
		};
	}

	private void deleteStudent(AppDao app) {
		int id = 24;
		System.out.println("Delete student: " + id);

		app.deleteStudentById(id);
		System.out.println("Deleted successfully!");

	}

	private void deleteCourseById(AppDao app) {
		int id = 22;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully!");

	}

	private void addMoreCoursesForStudent(AppDao app) {
		int id = 23;
		Student student = app.findStudentAndCoursesByStudentId(id);

		Course course1 = new Course("かなの書き方");
		Course course2 = new Course("漢字の読み方");
		Course course3 = new Course("りょうりの作り方");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Successfully enrolled to new courses!");
		app.update(student);

	}

	private void findCoursesAndStudents(AppDao app) {
		int id = 21;
		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());

	}

	private void createCourseAndStudents(AppDao app) {
		Course course = new Course("How to Become a Pickleball Pro");

		Student student1 = new Student("Sonic", "Hedgehog", "sonic@mail.com");
		Student student2 = new Student("Knuckles", "Echidna", 
										"knuckles@mail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		app.save(course);
		System.out.println("Successfully enrolled!");

	}

	private void deleteCourseAndReviews(AppDao app) {
		int id = 10;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully!");

	}

	private void retrieveCourseAndReview(AppDao app) {
		int id = 9;
		System.out.println("Get course and reviews from id: " + id);

		Course course = app.findCourseAndReviewsByCourseId(id);
		System.out.println(course);

		System.out.println(course.getReview());

	}

	private void createCourseAndReview(AppDao app) {
		Course course = new Course("How to be goated with the sauce!!!");
		
		course.addReview(new Review("Woah I'm goated"));
		course.addReview(new Review("Sauce was just OK"));
		course.addReview(new Review("Didn't get goated enough"));

		System.out.println("Course saved!");
		app.save(course);

	}

	private void deleteInstructorOnly(AppDao app) {
		int id = 8;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");

	}

	private void updateInstructor(AppDao app) {
		int id = 7;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		// update instructor data
		ins.setLastName("Dolor");
		System.out.println("Updated!");
		app.update(ins);

	}

	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		int id = 8;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());

	}

	private void findCoursesForInstructor(AppDao app) {
		int id = 7;

		// find instructor
		System.out.println("Find instructor id: " + id);
		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);

		// find instructor courses
		System.out.println("Courses from id: " + id);
		List<Course> courses = app.findCoursesByInstructorId(id);

		// associate the objects
		ins.setCourses(courses);

		// get courses
		System.out.println(ins.getCourses());

	}

	private void findInstructorWithCourse(AppDao app) {
		int id = 6;
		System.out.println("Finding instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());

	}

	private void createInstructorWithCourses(AppDao app) {
		Instructor ins = new Instructor("Lorem", 
										"Ipsum", 
										"lorem.ipsum@mail.com");
		InstructorDetail detail = new InstructorDetail("Computer Science", 
												"Making games");

		ins.setInstructorDetail(detail); // associate the objects

		// add courses
		Course course1 = new Course("Data Structures");
		Course course2 = new Course("Algorithms");

		ins.add(course1);
		ins.add(course2);

		// save
		System.out.println("Courses saved: " + ins.getCourses());
		app.save(ins);

	}

	private void deleteInstructorDetail(AppDao app) {
		int id = 3;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetailById(id);
		System.out.println("Deleted successfully!");
	
	}

	private void findInstructorDetail(AppDao app) {
		// get instructor detail object
		int id = 4;
		System.out.println("Finding id: " + id);

		InstructorDetail instructorDetail = app.findInstructorDetailById(id);

		// print instructor detail
		System.out.println("Instructor Detail: " + instructorDetail);

		// print associated instructor
		System.out.println("Instructor: " + instructorDetail.getInstructor());
	
	}

	private void deleteInstructor(AppDao app) {
		int id = 1;
		System.out.println("Deleting id: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	
	}

	private void findInstructor(AppDao app) {
		int id = 4;
		System.out.println("Finding id: " + id);

		Instructor instructor = app.findInstructorById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("Details: " + instructor.getInstructorDetail());
	
	}

	private void createInstructor(AppDao app) {
		// create instructor and details
		Instructor ins = new Instructor("John", 
										"Doe", 
										"john.doe@example.com");
		InstructorDetail insDetail = new InstructorDetail("Math", 
												"Watching movies");

		// associate the objects
		ins.setInstructorDetail(insDetail);

		// save the instructor
		System.out.println("Information saved!");
		app.save(ins);

	}

}
