package com.devtyagi.maalgaadi.dao;

import com.devtyagi.maalgaadi.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String username;

    private String name;

    private String email;

    private String mobileNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
