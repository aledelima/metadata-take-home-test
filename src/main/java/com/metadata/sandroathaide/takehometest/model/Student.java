package com.metadata.sandroathaide.takehometest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
//    private Map<Integer, Course> courses = new HashMap<>();
    private List<Course> courses = new ArrayList<>();

    public List<Course> addCourse(Course newCourse) {
//        this.courses.put(newCourse.getId(), newCourse);
        this.courses.add(newCourse);
//        return new ArrayList<>(this.courses.values());
        return new ArrayList<>(this.courses);
    }

    public List<Course> removeCourse(Course course) {
        this.courses.remove(course);
//        return new ArrayList<>(this.courses.values());
        return new ArrayList<>(this.courses);
    }

    public void clearCourses() {
        this.courses.clear();
    }
}
