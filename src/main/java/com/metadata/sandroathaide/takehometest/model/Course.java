package com.metadata.sandroathaide.takehometest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    @Builder.Default
    private List<Student> students = new ArrayList<>();

    public List<Student> addStudent(Student newStudent) {
        this.students.add(newStudent);
        return new ArrayList<>(this.students);
    }

    public List<Student> removeStudent(Student student) {
        this.students.remove(student);
        return new ArrayList<>(this.students);
    }

    public void clearStudents() {
        this.students.clear();
    }

}
