package com.bambidynamic.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecturer")
public class Lecturer extends Person {

    @Column(name = "hire_date")
    private LocalDate hireDate;

}
