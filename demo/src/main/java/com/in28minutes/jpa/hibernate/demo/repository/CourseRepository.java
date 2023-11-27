package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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

    public void playWithEntityManager(){
        Course course1 = new Course("Web services");
        em.persist(course1);
        Course course2 = new Course("Angular JS");
        em.persist(course2);
        
        em.flush();

        em.detach(course2);


        course1.setName("Web services - UPDATE");
        em.flush();

        course2.setName("Angular JS - UPDATE");
        em.flush();
    }






}
