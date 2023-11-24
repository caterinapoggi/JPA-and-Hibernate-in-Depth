package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public Course save(Course course){
        if(course.getId()==null){
            //insert
            em.persist(course);
        }
        else {
            //update
            em.merge(course);
        }
        return course;
    }
    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }







}