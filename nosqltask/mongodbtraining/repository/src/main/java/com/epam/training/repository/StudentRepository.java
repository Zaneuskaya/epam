package com.epam.training.repository;

import com.epam.training.config.PgDBConfig;
import com.epam.training.model.ExamResult;
import com.epam.training.model.Skill;
import com.epam.training.model.Student;
import com.epam.training.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public void insert(Student student) {
        mongoTemplate.insert(student);
    }
}
