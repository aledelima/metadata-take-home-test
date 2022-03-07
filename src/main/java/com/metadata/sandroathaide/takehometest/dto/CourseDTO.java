package com.metadata.sandroathaide.takehometest.dto;

import com.metadata.sandroathaide.takehometest.model.Course;
import lombok.Data;

@Data
public class CourseDTO {

    Integer id;
    String name;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
    }

}
