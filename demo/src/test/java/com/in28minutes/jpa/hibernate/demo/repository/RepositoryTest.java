package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class RepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Test
	void findById_basic() {
		Course course = repository.findById(10003L);
		assertEquals("Java", course.getName());
	}
	@Test
	@DirtiesContext
	void deleteById_basic() {
		repository.deleteById(10003L);
		assertNull(repository.findById(10003L));
	}

	@Test
	@DirtiesContext
	void save_basic() {
		//get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA", course.getName());

		//update
		course.setName("JPA- update");
		repository.save(course);

		//check value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA- update", course1.getName());
	}
}
