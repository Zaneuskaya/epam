package com.epam.training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student implements Serializable {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private Skill primarySkill;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String phone, LocalDate createdDate, LocalDate updatedDate, Skill primarySkill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.primarySkill = primarySkill;
    }
}
