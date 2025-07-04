package com.example.clgresult.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // ✅ FIX: Use correct import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.clgresult.model.Result;
import com.example.clgresult.model.Student;
import com.example.clgresult.repository.ResultRepository;
import com.example.clgresult.repository.StudentRepository;
import com.example.clgresult.service.GradeCalculatorService;

@Controller
public class AdminController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ResultRepository resultRepo;

    @Autowired
    private GradeCalculatorService gradeService;

    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "admin-dashboard"; // Make sure this file exists in /templates folder
    }
     @GetMapping("/add-student")
public String showAddStudentForm() {
    return "add-student"; // This is your HTML page name
}
@PostMapping("/add-student")
public String addStudent(@RequestParam String name, @RequestParam String rollNo) {
    Student student = new Student();
    student.setName(name);
    student.setRollNo(rollNo);
    studentRepo.save(student);
    return "redirect:/admin";
}

    @PostMapping("/add-result")
public String addResult(@RequestParam Long studentId,
                        @RequestParam String subject,
                        @RequestParam int marks) {

    // Correct: get Optional from repository
    Optional<Student> optionalStudent = studentRepo.findById(studentId);

    // Check if student exists
    if (!optionalStudent.isPresent()) {
        // Student not found — redirect back to admin with error
        return "redirect:/admin?error=studentNotFound";
    }
 
    // Student exists
    Student student = optionalStudent.get();

    // Calculate grade
    String grade = gradeService.calculateGrade(marks);

    // Create new result entry
    Result result = new Result();
    result.setStudent(student);
    result.setSubject(subject);
    result.setMarks(marks);
    result.setGrade(grade);

    // Save to DB
    resultRepo.save(result);

    return "redirect:/admin?success=marksAdded";
}

}
