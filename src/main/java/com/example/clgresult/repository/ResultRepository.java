package com.example.clgresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clgresult.model.Result;
import com.example.clgresult.model.Student;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudent(Student student);
}
