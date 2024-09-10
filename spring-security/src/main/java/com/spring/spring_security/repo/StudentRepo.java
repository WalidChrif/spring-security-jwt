package com.spring.spring_security.repo;

import com.spring.spring_security.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    Optional<Student> findByFirstName(String name);
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);

//    @Query(value = "select * from Student s where  s.id = :studentId ")
//    List<Student> getAllStudents(@Param("studentId") Long studentId);
    @Query(value = "select * from student s where  s.id = :studentId ", nativeQuery = true)
    List<Student> getAllStudentsNative(@Param("studentId") Long studentId);
}
