package com.project.muduck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.muduck.dto.request.auth.IdCheckRequestDto;
import com.project.muduck.dto.response.ResponseDto;
import com.project.muduck.service.AuthService;
import com.project.muduck.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    // private final UserService userService;

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(@RequestBody @Valid IdCheckRequestDto request) {
        ResponseEntity<ResponseDto> response = authService.idCheck(request);
        return response;
    }
}
