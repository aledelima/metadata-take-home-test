package com.metadata.sandroathaide.takehometest.config;

import com.metadata.sandroathaide.takehometest.model.Course;
import com.metadata.sandroathaide.takehometest.model.Student;
import com.metadata.sandroathaide.takehometest.repositories.CourseRepository;
import com.metadata.sandroathaide.takehometest.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
//@Profile("dev")
public class DBSeeding {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Bean
    public void databaseInitialization() {
        Course java = Course.builder()
                .name("Java for beginners")
                .build();

        Course git = Course.builder()
                .name("Versioning with Git")
                .build();

        Course docker = Course.builder()
                .name("Creating Docker Containers")
                .build();

        Course kubernetes = Course.builder()
                .name("Orchestrating Containers with Kubernetes")
                .build();

        Course microservices = Course.builder()
                .name("Introduction to Micro Services Architecture")
                .build();

        Course jenkins = Course.builder()
                .name("Building Pipelines with Jenkins")
                .build();

        Student sandroAthaide = Student.builder()
                .name("Sandro Athaide")
                .build();

        Student joaquimJoseSilvaXavier = Student.builder()
                .name("Joaquim José da Silva Xavier")
                .build();

        Student domPedroI = Student.builder()
                .name("Dom Pedro I")
                .build();

        Student baraoMaua = Student.builder()
                .name("Barão de Mauá")
                .build();

        Student duqueCaxias = Student.builder()
                .name("Duque de Caxias")
                .build();

        Student pedroAlvarasCabral = Student.builder()
                .name("Pedro Alvares Cabral")
                .build();

        Student osorioDuqueEstrada = Student.builder()
                .name("Osório Duque Estrada")
                .build();

        Student deodoroFonseca = Student.builder()
                .name("Marechal Deodoro da Fonseca")
                .build();

        courseRepository.saveAll(Arrays.asList(java, git, docker, microservices, kubernetes, jenkins));
//        studentRepository.save(sandroAthaide);

        sandroAthaide.addCourse(docker);
        sandroAthaide.addCourse(kubernetes);
        sandroAthaide.addCourse(jenkins);
        studentRepository.saveAll(Arrays.asList(sandroAthaide, joaquimJoseSilvaXavier, domPedroI,
                baraoMaua, duqueCaxias, pedroAlvarasCabral, osorioDuqueEstrada, deodoroFonseca));

    }

}
