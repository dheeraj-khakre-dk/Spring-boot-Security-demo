package com.example.springBootStudent.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "users_sequence", allocationSize = 1, sequenceName = "users_sequence")
    @GeneratedValue(generator = "users_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String userName;
    private String password;
}
