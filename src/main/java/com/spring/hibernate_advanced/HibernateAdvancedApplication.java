package com.spring.hibernate_advanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_advanced.dao.AppDao;
import com.spring.hibernate_advanced.entity.Course;
import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;

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
			createInstructorWithCourses(app);
		};
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
