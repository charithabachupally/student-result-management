package com.example.clgresult.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clgresult.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    public Optional findById(Long studentId);
}

