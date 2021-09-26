package com.epam.training.controller;

import com.epam.training.model.ExamResult;
import com.epam.training.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;

    @GetMapping
    public List<ExamResult> getExamResults() {
        return examResultService.getExamResults();
    }

    @GetMapping("/migrate")
    public void migrateExamResults() {
        examResultService.migrateExamResults();
    }
}
