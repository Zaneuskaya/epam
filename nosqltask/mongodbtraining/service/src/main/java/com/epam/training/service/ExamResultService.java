package com.epam.training.service;

import com.epam.training.model.ExamResult;
import com.epam.training.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    public ExamResultService(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    public void create(){
    }

    public List<ExamResult> getExamResults(){
        return examResultRepository.findAll();
    }

    public void migrateExamResults(){
        try {
            examResultRepository.migrateAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
