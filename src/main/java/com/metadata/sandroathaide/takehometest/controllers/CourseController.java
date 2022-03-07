package com.metadata.sandroathaide.takehometest.controllers;

import com.metadata.sandroathaide.takehometest.dto.CourseDTO;
import com.metadata.sandroathaide.takehometest.dto.CourseNewDTO;
import com.metadata.sandroathaide.takehometest.dto.StudentDTO;
import com.metadata.sandroathaide.takehometest.dto.StudentNewDTO;
import com.metadata.sandroathaide.takehometest.model.Course;
import com.metadata.sandroathaide.takehometest.model.Student;
import com.metadata.sandroathaide.takehometest.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> findAll() {
        return courseService.findAll().stream()
                .map(c -> new CourseDTO(c)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable("id") Integer id) {
        return new CourseDTO(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> insert(@RequestBody CourseNewDTO dto, UriComponentsBuilder uriBuilder) {

        Course course = courseService.insert(Course.builder().name(dto.getName()).build());
        URI uri = uriBuilder.path("/courses/{id}")
                .buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(new CourseDTO(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Integer id, @RequestBody CourseNewDTO dto) {
        Course course = Course.builder()
                .id(id)
                .name(dto.getName())
                .build();
        return ResponseEntity.ok(new CourseDTO(courseService.update(course)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filterByStudentId/{studentId}")
    public List<CourseDTO> filterByCourse(@PathVariable Integer studentId) {
        return courseService.findByStudentId(studentId).stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/filterWithoutStudents")
    public List<CourseDTO> filterWithoutCourses() {
        return courseService.findCoursesWithNoStudents().stream().map(CourseDTO::new).collect(Collectors.toList());
    }

}
