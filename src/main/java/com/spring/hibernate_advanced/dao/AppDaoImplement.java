package com.spring.hibernate_advanced.dao;

import com.spring.hibernate_advanced.entity.Course;
import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;
import com.spring.hibernate_advanced.entity.Review;
import com.spring.hibernate_advanced.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

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

    /**
     * Persists a new {@link Instructor} along with 
     * its associated {@link InstructorDetail}.
     *
     * @param theInstructor the instructor entity to save
     */
    @Override
    @Transactional 
    public void save(Instructor instructor) {
       em.persist(instructor);
    }
/**
     * Retrieves an {@link Instructor} by unique ID.
     *
     * @param id the primary key identifier
     * @return the matching {@link Instructor} entity, 
     *          or {@code null} if not found
     */
    @Override
    public Instructor findInstructorById(int id) {
        return em.find(Instructor.class, id);
    }

    /**
     * Deletes an {@link Instructor} by unique ID.
     *
     * @param id the primary key identifier of the instructor to delete
     */
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

    /**
     * Retrieves an {@link InstructorDetail} by unique ID.
     *
     * @param id the primary key identifier
     * @return the matching {@link InstructorDetail} entity, 
     *          or {@code null} if not found
     */
    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return em.find(InstructorDetail.class, id);

    }

    /**
     * Deletes an {@link InstructorDetail} by unique ID 
     * without cascading to the associated instructor.
     *
     * @param id the primary key identifier of the detail record to remove
     */
    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail insDetail = em.find(InstructorDetail.class, id);

        // remove the associated object reference
        insDetail.getInstructor().setInstructorDetail(null);
        
        em.remove(insDetail);

    }

    /**
     * Retrieves a list of {@link Course} entities taught by 
     * a specific instructor.
     *
     * @param instructorId the instructor ID
     * @return a list of assigned courses
     */
    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        
        TypedQuery<Course> query = em.createQuery(
            "FROM Course WHERE instructor.id=:data", Course.class);

        query.setParameter("data", instructorId);

        List<Course> courses = query.getResultList();
        return courses;
    
    }

    /**
     * Retrieves an {@link Instructor} entity and eagerly fetches 
     * assigned courses via JOIN FETCH.
     *
     * @param id the instructor ID
     * @return the {@link Instructor} entity containing courses
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        TypedQuery<Instructor> query = em.createQuery(
            "SELECT i from Instructor i JOIN FETCH i.courses WHERE i.id=:data",
            Instructor.class);
            
        query.setParameter("data", id);

        Instructor in = query.getSingleResult();
        return in;

    }

    /**
     * Updates an existing {@link Instructor} record.
     *
     * @param instructor the instructor entity with updated fields
     */
    @Override
    @Transactional 
    public void update(Instructor instructor) {
        em.merge(instructor);
    }

    /**
     * Persists a {@link Course} entity along with 
     * its associated {@link Review} objects.
     *
     * @param course the course entity containing reviews
     */
    @Override
    @Transactional 
    public void save(Course course) {
        em.persist(course);
    }

    /**
     * Retrieves a {@link Course} entity and eagerly fetches its reviews.
     *
     * @param id the course ID
     * @return the matching {@link Course} with reviews initialized
     */
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

    /**
     * Deletes a {@link Course} entity by unique ID.
     *
     * @param id the primary key identifier of the course to delete
     */
    @Override
    @Transactional
    public void deleteCourseById(int id) {

        Course course = em.find(Course.class, id);
        em.remove(course);

    }

    /**
     * Retrieves a {@link Course} entity and eagerly fetches 
     * its enrolled {@link Student} list.
     *
     * @param id the course ID
     * @return the matching {@link Course} with students initialized
     */
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

    /**
     * Retrieves a {@link Student} entity and eagerly fetches 
     * their enrolled {@link Course} list.
     *
     * @param id the student ID
     * @return the matching {@link Student} with courses initialized
     */
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

    /**
     * Updates an existing {@link Student} entity.
     *
     * @param student the student entity with updated fields
     */
    @Override
    @Transactional 
    public void update(Student student) {
        em.merge(student);
        
    }

    /**
     * Deletes a {@link Student} by unique ID.
     *
     * @param id the primary key identifier of the student to delete
     */
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
