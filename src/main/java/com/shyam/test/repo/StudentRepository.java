package com.shyam.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.test.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
