package com.metadata.sandroathaide.takehometest.dto;

import com.metadata.sandroathaide.takehometest.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class StudentDTO {

    private Integer id;
    private String name;
    @Builder.Default
    private List<CourseDTO> courses = new ArrayList<>();

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        List<CourseDTO> cDtos = student.getCourses().stream().map(c -> new CourseDTO(c)).collect(Collectors.toList());
        this.courses = cDtos;
    }

}
