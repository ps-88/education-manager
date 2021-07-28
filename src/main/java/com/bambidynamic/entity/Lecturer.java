package com.bambidynamic.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "lecturer")
public class Lecturer extends Person {



}
