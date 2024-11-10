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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tel_auth_numbers")
@Entity(name = "tel_auth_numbers")
public class TelAuthEntity {

    @Id
    private String telNumber;
    private String authNumber;
    
}
