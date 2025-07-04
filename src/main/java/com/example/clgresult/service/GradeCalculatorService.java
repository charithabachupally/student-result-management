package com.example.clgresult.service;

import org.springframework.stereotype.Service;

@Service
public class GradeCalculatorService {
    public String calculateGrade(int marks) {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        return "F";
    }
}

