package com.spring.hibernate_advanced;

import com.spring.hibernate_advanced.dao.AppDao;
import com.spring.hibernate_advanced.entity.Course;
import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;
import com.spring.hibernate_advanced.entity.Review;
import com.spring.hibernate_advanced.entity.Student;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * HibernateAdvancedApplication
 * Main entry point for the Hibernate Advanced Spring Boot Application.
 * <p>
 * Demonstrates basic and advanced JPA operations including One-to-One, 
 * One-to-Many, and Many-to-Many relationship management using {@link AppDao}.
 * </p>
 * 
 * @author Felisa Bascug
 */
@SpringBootApplication
public class HibernateAdvancedApplication {

	/**
	 * Main method used to launch the Spring Boot application context.
	 *
	 * @param args command-line arguments passed during startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(HibernateAdvancedApplication.class, 
		args);
	}

	/**
	 * Creates a {@link CommandLineRunner} bean executed immediately after
	 * the application context is loaded.
	 *
	 * @param app the injected Data Access Object bean
	 * @return a runner lambda executing demo JPA operations
	 */
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

	/**
	 * Demonstrates deleting a {@link Student} by unique primary key ID.
	 *
	 * @param app the DAO instance used to perform deletion
	 */
	private void deleteStudent(AppDao app) {
		int id = 24;
		System.out.println("Delete student: " + id);

		app.deleteStudentById(id);
		System.out.println("Deleted successfully!");

	}

	/**
	 * Demonstrates deleting a {@link Course} by unique primary key ID.
	 *
	 * @param app the DAO instance used to perform deletion
	 */
	private void deleteCourseById(AppDao app) {
		int id = 22;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully!");

	}

	/**
	 * Demonstrates enrolling an existing {@link Student} 
	 * into additional {@link Course} entities.
	 *
	 * @param appDao the DAO instance used to update student course 
	 * 		assignments
	 */
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

	/**
	 * Demonstrates retrieving a {@link Course} along 
	 * with its enrolled {@link Student} entities.
	 *
	 * @param appDao the DAO instance used to fetch data
	 */
	private void findCoursesAndStudents(AppDao app) {
		int id = 21;
		Course course = app.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());

	}

	/**
	 * Demonstrates creating a new {@link Course} and 
	 * enrolling multiple {@link Student} entities into it.
	 *
	 * @param app the DAO instance used to save the course and students
	 */
	private void createCourseAndStudents(AppDao app) {
		Course course = new Course("How to Become a Pickleball Pro");

		Student student1 = new Student("Sonic", "Hedgehog", 
						"sonic@mail.com");
		Student student2 = new Student("Knuckles", "Echidna", 
						"knuckles@mail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		app.save(course);
		System.out.println("Successfully enrolled!");

	}

	/**
	 * Demonstrates deleting a {@link Course} along with 
	 * its associated reviews by ID.
	 *
	 * @param app the DAO instance used to perform deletion
	 */
	private void deleteCourseAndReviews(AppDao app) {
		int id = 10;
		System.out.println("Delete course: " + id);

		app.deleteCourseById(id);
		System.out.println("Deleted successfully!");

	}

	/**
	 * Demonstrates retrieving a {@link Course} along with 
	 * its associated {@link Review} list by course ID.
	 *
	 * @param app the DAO instance used to fetch data
	 */
	private void retrieveCourseAndReview(AppDao app) {
		int id = 9;
		System.out.println("Get course and reviews from id: " + id);

		Course course = app.findCourseAndReviewsByCourseId(id);
		System.out.println(course);

		System.out.println(course.getReview());

	}

	/**
	 * Demonstrates creating a new {@link Course} and 
	 * attaching multiple {@link Review} objects to it.
	 *
	 * @param app the DAO instance used to save the course with reviews
	 */
	private void createCourseAndReview(AppDao app) {
		Course course = new Course(
			"How to be goated with the sauce!!!");
		
		course.addReview(new Review("Woah I'm goated"));
		course.addReview(new Review("Sauce was just OK"));
		course.addReview(new Review("Didn't get goated enough"));

		System.out.println("Course saved!");
		app.save(course);

	}

	/**
	 * Demonstrates deleting an {@link Instructor} 
	 * without deleting associated entities.
	 *
	 * @param app the DAO instance used to perform deletion
	 */
	private void deleteInstructorOnly(AppDao app) {
		int id = 8;
		System.out.println("Delete instructor: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");

	}

	/**
	 * Demonstrates updating an existing {@link Instructor} 
	 * record in the database.
	 *
	 * @param app the DAO instance used to update instructor data
	 */
	private void updateInstructor(AppDao app) {
		int id = 7;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		// update instructor data
		ins.setLastName("Dolor");
		System.out.println("Updated!");
		app.update(ins);

	}

	/**
	 * Demonstrates retrieving an {@link Instructor} 
	 * along with their courses using JOIN FETCH query optimization.
	 *
	 * @param app the DAO instance used to fetch data
	 */
	private void findInstructorWithCoursesJoinFetch(AppDao app) {
		int id = 8;
		System.out.println("Find instructor id: " + id);

		Instructor ins = app.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());

	}

	/**
	 * Demonstrates finding an {@link Instructor} and 
	 * explicitly querying their associated {@link Course} list.
	 *
	 * @param app the DAO instance used to fetch data
	 */
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

	/**
	 * Demonstrates fetching an {@link Instructor} 
	 * and getting their courses list.
	 *
	 * @param app the DAO instance used to fetch data
	 */
	private void findInstructorWithCourse(AppDao app) {
		int id = 6;
		System.out.println("Finding instructor id: " + id);

		Instructor ins = app.findInstructorById(id);

		System.out.println("Instructor: " + ins);
		System.out.println("Courses: " + ins.getCourses());

	}

	/**
	 * Demonstrates persisting a new {@link Instructor} 
	 * along with an {@link InstructorDetail} 
	 * and multiple {@link Course} entities.
	 *
	 * @param app the DAO instance used to save data
	 */
	private void createInstructorWithCourses(AppDao app) {
		Instructor ins = new Instructor("Lorem", 
						"Ipsum", 
						"lorem.ipsum@mail.com");
		InstructorDetail detail = new InstructorDetail(
			"Computer Science", 
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

	/**
	 * Demonstrates deleting an {@link InstructorDetail} record by ID.
	 *
	 * @param app the DAO instance used to perform deletion
	 */
	private void deleteInstructorDetail(AppDao app) {
		int id = 3;
		System.out.println("Delete id: " + id);

		app.deleteInstructorDetailById(id);
		System.out.println("Deleted successfully!");
	
	}

	/**
	 * Demonstrates retrieving an {@link InstructorDetail} 
	 * and its associated {@link Instructor}.
	 *
	 * @param app the DAO instance used to fetch data
	 */
	private void findInstructorDetail(AppDao app) {
		// get instructor detail object
		int id = 4;
		System.out.println("Finding id: " + id);

		InstructorDetail instructorDetail = 
			app.findInstructorDetailById(id);

		// print instructor detail
		System.out.println("Instructor Detail: " + instructorDetail);

		// print associated instructor
		System.out.println("Instructor: " + 
			instructorDetail.getInstructor());
	
	}

	/**
	 * Demonstrates deleting an {@link Instructor} by unique ID.
	 *
	 * @param app the DAO instance used to perform deletion
	 */
	private void deleteInstructor(AppDao app) {
		int id = 1;
		System.out.println("Deleting id: " + id);

		app.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	
	}

	/**
	 * Demonstrates finding an {@link Instructor} and 
	 * printing their details.
	 *
	 * @param app the DAO instance used to fetch data
	 */
	private void findInstructor(AppDao app) {
		int id = 4;
		System.out.println("Finding id: " + id);

		Instructor instructor = app.findInstructorById(id);

		System.out.println("Instructor: " + instructor);
		System.out.println("Details: " + instructor.getInstructorDetail());
	
	}

	/**
	 * Demonstrates persisting a new {@link Instructor} 
	 * with an associated {@link InstructorDetail}.
	 *
	 * @param app the DAO instance used to save data
	 */
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
