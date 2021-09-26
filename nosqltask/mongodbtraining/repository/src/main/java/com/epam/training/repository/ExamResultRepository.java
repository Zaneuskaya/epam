package com.epam.training.repository;

import com.epam.training.config.PgDBConfig;
import com.epam.training.model.ExamResult;
import com.epam.training.model.Skill;
import com.epam.training.model.Student;
import com.epam.training.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class ExamResultRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ExamResult> findAll() {
        return mongoTemplate.findAll(ExamResult.class);
    }

    public void insert(ExamResult examResult) {
        mongoTemplate.insert(examResult);
    }

    public void migrateAll() throws SQLException {
        try (Connection con = PgDBConfig.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT mark, sub.name, sub.tutor_name, st.first_name, st.last_name, st.date_of_birth, st.phone_number, st.created_date, st.updated_date, sk.name as skill_name FROM epam_training.exam_results er left join\n" +
                    "epam_training.subjects sub on er.subject_id = sub.id left join\n" +
                    " epam_training.students st on er.student_id = st.id left join epam_training.skills sk on st.primary_skill = sk.id ");

            while(rs.next()){
                Skill skill = new Skill(rs.getString("name"));
                Query query = new Query();
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone_number");
                LocalDate createdDate = rs.getDate("created_date").toLocalDate();
                LocalDate updatedDate = rs.getDate("updated_date").toLocalDate();
                LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
                query.addCriteria(Criteria.where("first_name").is(firstName).and("last_name").is(lastName).and("created_date").is(createdDate));
                Student student = new Student(firstName, lastName, dateOfBirth, phone, createdDate, updatedDate, skill);
                if (mongoTemplate.exists(query, Student.class)){
                    student = mongoTemplate.findOne(query, Student.class);
                }
                else{
                    mongoTemplate.insert(student);
                }
                Subject subject = new Subject(rs.getString("name"),rs.getString("tutor_name"));
                ExamResult examResult = new ExamResult(rs.getByte("mark"), subject, student);
                mongoTemplate.insert(examResult);

            }
        }
    }
}
