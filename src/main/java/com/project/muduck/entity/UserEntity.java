package com.project.muduck.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class UserEntity {
    
    @Id
    private String userId;
    private String password;
    private String name;
    private String telNumber;
    private String address;
    private String joinPath;
    private String snsId;
    private Boolean isAdmin = false;
    private String profilePicture;


}
