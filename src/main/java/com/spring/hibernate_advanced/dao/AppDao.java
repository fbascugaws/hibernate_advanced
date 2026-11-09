package com.spring.hibernate_advanced.dao;

import com.spring.hibernate_advanced.entity.Instructor;
import com.spring.hibernate_advanced.entity.InstructorDetail;

public interface AppDao {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);

}
