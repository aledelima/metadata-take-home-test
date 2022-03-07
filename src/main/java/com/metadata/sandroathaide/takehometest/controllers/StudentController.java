package com.metadata.sandroathaide.takehometest.controllers;

import com.metadata.sandroathaide.takehometest.dto.CourseSubscriptionDTO;
import com.metadata.sandroathaide.takehometest.dto.StudentDTO;
import com.metadata.sandroathaide.takehometest.dto.StudentNewDTO;
import com.metadata.sandroathaide.takehometest.model.Student;
import com.metadata.sandroathaide.takehometest.services.CourseService;
import com.metadata.sandroathaide.takehometest.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> findAll() {
            return studentService.findAll()
                    .stream().map(StudentDTO::new).collect(Collectors.toList());
        }

    @GetMapping("/{id}")
    public StudentDTO findById(@PathVariable("id") Integer id) {
        return new StudentDTO(studentService.findById(id));
    }

    @GetMapping("/filterByCourseId/{courseId}")
    public List<StudentDTO> filterByCourse(@PathVariable Integer courseId) {
        return studentService.findByCourseId(courseId).stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/filterWithoutCourses")
    public List<StudentDTO> filterWithoutCourses() {
        return studentService.findStudentsWithNoCourse().stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> insert(@RequestBody StudentNewDTO dto, UriComponentsBuilder uriBuilder) {

        Student student = studentService.insert(Student.builder().name(dto.getName()).build());
        URI uri = uriBuilder.path("/students/{id}")
                .buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(new StudentDTO(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Integer id, @RequestBody StudentNewDTO dto) {
        Student student = Student.builder()
                .id(id)
                .name(dto.getName())
                .build();
        return ResponseEntity.ok(new StudentDTO(studentService.update(student)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/subscribeForCourse")
    public ResponseEntity<StudentDTO> subscribeStudentToCourse(@RequestBody CourseSubscriptionDTO dto) {
        return ResponseEntity.ok(new StudentDTO(studentService.subscribeStudentToCourse(dto.getStudentId(), dto.getCourseId())));
    }

    @PostMapping("/unsubscribeForCourse")
    public ResponseEntity<StudentDTO> unsubscribeStudentToCourse(@RequestBody CourseSubscriptionDTO dto) {
        return ResponseEntity.ok(new StudentDTO(studentService.unsubscribeStudentToCourse(dto.getStudentId(), dto.getCourseId())));
    }

}
