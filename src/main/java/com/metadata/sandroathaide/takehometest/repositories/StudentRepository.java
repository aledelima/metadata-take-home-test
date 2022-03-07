package com.metadata.sandroathaide.takehometest.repositories;

import com.metadata.sandroathaide.takehometest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s JOIN s.courses cs WHERE cs.id = :courseId")
    List<Student> findByCourseId(@Param("courseId") Integer courseId);

    @Query("SELECT s FROM Student s WHERE s.courses IS EMPTY")
    List<Student> findAllStudentsWithNoCourse();

}
