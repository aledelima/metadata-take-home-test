package com.metadata.sandroathaide.takehometest.services;

import com.metadata.sandroathaide.takehometest.model.Course;
import com.metadata.sandroathaide.takehometest.model.Student;
import com.metadata.sandroathaide.takehometest.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private CourseService courseService;

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping
    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Student.class, "Object Not Found. Id: " + id));
    }

    public Student insert(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    public Student update(@RequestBody Student newStudent) {
        Student savedStudent = this.findById(newStudent.getId());

        savedStudent.setName(newStudent.getName());
        return studentRepository.save(savedStudent);
    }

    public void delete(Integer id) {
        this.findById(id);
        studentRepository.deleteById(id);
    }

    public List<Student> findByCourseId(Integer courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    public List<Student> findStudentsWithNoCourse() {
        return studentRepository.findAllStudentsWithNoCourse();
    }

    public Student subscribeStudentToCourse(Integer studentId, Integer courseId) {
        Student student = this.findById(studentId);
        Course course = courseService.findById(courseId);

        if (student.getCourses().contains(course))
            throw new DataIntegrityViolationException("Course already subscribed for this student.");

        student.addCourse(course);
        return this.update(student);
    }

    public Student unsubscribeStudentToCourse(Integer studentId, Integer courseId) {
        Student student = this.findById(studentId);
        Course course = courseService.findById(courseId);

        if (!student.getCourses().contains(course))
            throw new DataIntegrityViolationException("Course not subscribed for this student.");

        student.removeCourse(course);
        return this.update(student);
    }
}
