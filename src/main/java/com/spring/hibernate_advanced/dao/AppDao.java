package com.spring.hibernate_advanced.dao;

import java.util.List;

import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;
import com.spring.hibernate_advanced.entity.Student;
import com.spring.hibernate_advanced.entity.Course;

public interface AppDao {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int instructorId);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    void save(Course course);
    Course findCourseAndReviewsByCourseId(int id);
    void deleteCourseById(int id);
    Course findCourseAndStudentByCourseId(int id);
    Student findStudentAndCoursesByStudentId(int id);
    void update(Student student);
    void deleteStudentById(int id);

}
