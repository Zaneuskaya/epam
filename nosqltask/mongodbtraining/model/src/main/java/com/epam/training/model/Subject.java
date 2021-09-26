package com.epam.training.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Subject implements Serializable {

    private String name;
    private String tutorName;

    public Subject(String name, String tutorName) {
        this.name = name;
        this.tutorName = tutorName;
    }
}

