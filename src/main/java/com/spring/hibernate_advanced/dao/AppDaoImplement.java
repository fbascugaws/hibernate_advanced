package com.spring.hibernate_advanced.dao;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository 
public class AppDaoImplement implements AppDao {

    private EntityManager em;

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

}
