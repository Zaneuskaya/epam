package com.epam.training.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection="exams")
public class ExamResult implements Serializable {

    @Id
    private ObjectId id;
    private Byte mark;
    private Subject subject;
    @DBRef(lazy = true)
    private Student student;

    public ExamResult(Byte mark, Subject subject, Student student) {
        this.mark = mark;
        this.subject = subject;
        this.student = student;
    }
}
