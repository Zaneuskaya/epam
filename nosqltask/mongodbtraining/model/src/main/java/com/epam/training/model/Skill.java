package com.epam.training.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Skill implements Serializable {
    private String name;

    public Skill(String name) {
        this.name = name;
    }
}
