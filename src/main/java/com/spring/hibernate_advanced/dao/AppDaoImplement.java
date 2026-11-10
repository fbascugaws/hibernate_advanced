package com.spring.hibernate_advanced.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.hibernate_advanced.entity.Course;
import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;
import com.spring.hibernate_advanced.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * AppDaoImplement
 * Implementation of the {@link AppDao} interface 
 * using JPA {@link EntityManager}.
 * <p>
 * Handles database access logic across relational mappings including
 * single queries, JOIN FETCH optimization, updates, and deletions.
 * </p>
 * 
 * @author Felisa Bascug
 */
@Repository 
public class AppDaoImplement implements AppDao {

    /**
     * JPA Entity Manager instance injected via constructor.
     */
    private EntityManager em;

    /**
     * Constructs a new {@code AppDaoImplement} with 
     * the provided {@link EntityManager}.
     *
     * @param em the JPA entity manager instance
     */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional 
    public void save(Instructor instructor) {
       em.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return em.find(Instructor.class, id);
    }

    @Override
    @Transactional 
    public void deleteInstructorById(int id) {
        // retreive instructor
        Instructor instructor = em.find(Instructor.class, id);

        // break associations of courses
        List<Course> courses = instructor.getCourses();
        for (Course c : courses) {
            c.setInstructor(null);
        }

        // delete instructor
        em.remove(instructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return em.find(InstructorDetail.class, id);

    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail insDetail = em.find(InstructorDetail.class, id);

        // remove the associated object reference
        insDetail.getInstructor().setInstructorDetail(null);
        
        em.remove(insDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        
        TypedQuery<Course> query = em.createQuery(
            "FROM Course WHERE instructor.id=:data", Course.class);

        query.setParameter("data", instructorId);

        List<Course> courses = query.getResultList();
        return courses;
    
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        TypedQuery<Instructor> query = em.createQuery(
            "SELECT i from Instructor i JOIN FETCH i.courses WHERE i.id=:data",
            Instructor.class);
            
        query.setParameter("data", id);

        Instructor in = query.getSingleResult();
        return in;

    }

    @Override
    @Transactional 
    public void update(Instructor instructor) {
        em.merge(instructor);
    }

    @Override
    @Transactional 
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c " +
            "JOIN FETCH c.review " +
            "WHERE c.id=:data", Course.class);
        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        Course course = em.find(Course.class, id);
        em.remove(course);

    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c " +
            "JOIN FETCH c.students " + 
            "WHERE c.id=:data", Course.class);
        query.setParameter("data", id);

        Course course = query.getSingleResult();
        return course;
        
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = em.createQuery(
            "SELECT s FROM Student s " +
            "JOIN FETCH s.courses " + 
            "WHERE s.id=:data", Student.class);
        query.setParameter("data", id);

        Student student = query.getSingleResult();
        return student;

    }

    @Override
    @Transactional 
    public void update(Student student) {
        em.merge(student);
        
    }

    @Override
    @Transactional 
    public void deleteStudentById(int id) {
        Student student = em.find(Student.class, id);

        if (student != null) {
            List<Course> courses = student.getCourses();

            for (Course c : courses) {
                c.getStudents().remove(student);
            }

        }

        em.remove(student);
    } 

}
