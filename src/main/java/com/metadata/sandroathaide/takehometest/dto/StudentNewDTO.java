package com.metadata.sandroathaide.takehometest.dto;

import com.metadata.sandroathaide.takehometest.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentNewDTO {

    private String name;

    public Student toStudent() {
        return Student.builder()
                .name(this.name)
                .build();
    }

}
