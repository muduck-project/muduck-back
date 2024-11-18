package com.project.muduck.entity;

import com.project.muduck.dto.request.auth.SignUpRequestDto;

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
    private String joinPath = "home";
    private String snsId;
    private Boolean isAdmin = false;
    private String profilePicture;

    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.joinPath = dto.getJoinPath();
    }

}
