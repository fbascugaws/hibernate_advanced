package com.spring.hibernate_advanced.dao;

import com.spring.hibernate_advanced.entity.Course;
import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;
import com.spring.hibernate_advanced.entity.Review;
import com.spring.hibernate_advanced.entity.Student;

import java.util.List;

/**
 * AppDao
 * Data Access Object (DAO) interface defining operations for 
 * advanced entity mappings, including One-to-One, One-to-Many, 
 * and Many-to-Many relationships.
 * 
 * @author fbascugaws
 */
public interface AppDao {

    /**
     * Persists a new {@link Instructor} along with 
     * its associated {@link InstructorDetail}.
     *
     * @param theInstructor the instructor entity to save
     */
    void save(Instructor instructor);

    /**
     * Retrieves an {@link Instructor} by unique ID.
     *
     * @param id the primary key identifier
     * @return the matching {@link Instructor} entity, 
     *          or {@code null} if not found
     */
    Instructor findInstructorById(int id);

    /**
     * Deletes an {@link Instructor} by unique ID.
     *
     * @param id the primary key identifier of the instructor to delete
     */
    void deleteInstructorById(int id);

    /**
     * Retrieves an {@link InstructorDetail} by unique ID.
     *
     * @param id the primary key identifier
     * @return the matching {@link InstructorDetail} entity, 
     *          or {@code null} if not found
     */
    InstructorDetail findInstructorDetailById(int id);

    /**
     * Deletes an {@link InstructorDetail} by unique ID 
     * without cascading to the associated instructor.
     *
     * @param id the primary key identifier of the detail record to remove
     */
    void deleteInstructorDetailById(int id);

    /**
     * Retrieves a list of {@link Course} entities taught by 
     * a specific instructor.
     *
     * @param instructorId the instructor ID
     * @return a list of assigned courses
     */
    List<Course> findCoursesByInstructorId(int instructorId);

    /**
     * Retrieves an {@link Instructor} entity and eagerly fetches 
     * assigned courses via JOIN FETCH.
     *
     * @param id the instructor ID
     * @return the {@link Instructor} entity containing courses
     */
    Instructor findInstructorByIdJoinFetch(int id);

    /**
     * Updates an existing {@link Instructor} record.
     *
     * @param instructor the instructor entity with updated fields
     */
    void update(Instructor instructor);

    /**
     * Persists a {@link Course} entity along with 
     * its associated {@link Review} objects.
     *
     * @param course the course entity containing reviews
     */
    void save(Course course);

    /**
     * Retrieves a {@link Course} entity and eagerly fetches its reviews.
     *
     * @param id the course ID
     * @return the matching {@link Course} with reviews initialized
     */
    Course findCourseAndReviewsByCourseId(int id);

    /**
     * Deletes a {@link Course} entity by unique ID.
     *
     * @param id the primary key identifier of the course to delete
     */
    void deleteCourseById(int id);

    /**
     * Retrieves a {@link Course} entity and eagerly fetches 
     * its enrolled {@link Student} list.
     *
     * @param id the course ID
     * @return the matching {@link Course} with students initialized
     */
    Course findCourseAndStudentByCourseId(int id);

    /**
     * Retrieves a {@link Student} entity and eagerly fetches 
     * their enrolled {@link Course} list.
     *
     * @param id the student ID
     * @return the matching {@link Student} with courses initialized
     */
    Student findStudentAndCoursesByStudentId(int id);

    /**
     * Updates an existing {@link Student} entity.
     *
     * @param student the student entity with updated fields
     */
    void update(Student student);

    /**
     * Deletes a {@link Student} by unique ID.
     *
     * @param id the primary key identifier of the student to delete
     */
    void deleteStudentById(int id);

}
