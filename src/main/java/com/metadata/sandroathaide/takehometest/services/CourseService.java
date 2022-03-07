package com.metadata.sandroathaide.takehometest.services;

import com.metadata.sandroathaide.takehometest.model.Course;
import com.metadata.sandroathaide.takehometest.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @GetMapping
    public Course findById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Course.class, "Object Not Found. Id: " + id));
    }

    public Course insert(Course course) {
        courseRepository.findByName(course.getName())
                .ifPresent(s -> {throw new DataIntegrityViolationException("Another course with the same name is already registred.");});

        return courseRepository.save(course);
    }

    public Course update(Course newCourse) {
        Course savedCourse = this.findById(newCourse.getId());

        savedCourse.setName(newCourse.getName());
        return courseRepository.save(savedCourse);
    }

    public void delete(Integer id) {
        this.findById(id);
        courseRepository.deleteById(id);
    }

    public List<Course> findCoursesWithNoStudents() {
        return courseRepository.findCoursesWithNoStudents();
    }

    public List<Course> findByStudentId(Integer studentId) {
        return courseRepository.findByStudentId(studentId);
    }

}
