package com.project.muduck.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdTelNumberRequestDto {
    
    @NotBlank
    private String userId;

    @NotBlank
    @Pattern(regexp = "^[0-9]{11}$")
    private String telNumber;
}
