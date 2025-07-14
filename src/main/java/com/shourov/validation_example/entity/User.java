package com.shourov.validation_example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS_TBL")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private int age;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String nationality;
}
