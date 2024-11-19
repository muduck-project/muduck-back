package com.project.muduck.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindIdRequestDto {
    
    @NotBlank
    private String telNumber;
    @NotBlank
    private String authNumber;
}