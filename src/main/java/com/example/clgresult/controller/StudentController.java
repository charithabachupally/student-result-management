package com.example.clgresult.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.clgresult.model.Result;
import com.example.clgresult.model.Student;
import com.example.clgresult.repository.ResultRepository;
import com.example.clgresult.repository.StudentRepository;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ResultRepository resultRepo;

    @GetMapping("/student/{id}")
    public String viewResult(@PathVariable Long id, Model model) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        
        if (!optionalStudent.isPresent()) {
            // Optional: show a "student not found" page or message
            return "redirect:/error";
        }

        Student student = optionalStudent.get();
        List<Result> results = resultRepo.findByStudent(student);

        model.addAttribute("student", student);
        model.addAttribute("results", results);

        return "student-result"; // must match the Thymeleaf HTML file name
    }
}
