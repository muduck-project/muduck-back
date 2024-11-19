package com.project.muduck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.muduck.dto.request.auth.FindIdRequestDto;
import com.project.muduck.dto.request.auth.FindPasswordRequestDto;
import com.project.muduck.dto.request.auth.FindTelNumberRequestDto;
import com.project.muduck.dto.request.auth.IdCheckRequestDto;
import com.project.muduck.dto.request.auth.IdTelNumberRequestDto;
import com.project.muduck.dto.request.auth.SignInRequestDto;
import com.project.muduck.dto.request.auth.SignUpRequestDto;
import com.project.muduck.dto.request.auth.TelAuthCheckRequestDto;
import com.project.muduck.dto.request.auth.TelAuthRequsetDto;
import com.project.muduck.dto.response.ResponseDto;
import com.project.muduck.dto.response.auth.FindIdResponseDto;
import com.project.muduck.dto.response.auth.FindPasswordResponseDto;
import com.project.muduck.dto.response.auth.SignInResponseDto;
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

    @PostMapping("/tel-auth")
    public ResponseEntity<ResponseDto> telAuth(@RequestBody @Valid TelAuthRequsetDto request) {
        ResponseEntity<ResponseDto> response = authService.telAuth(request);
        return response;
    }

    @PostMapping("tel-auth-check")
    public ResponseEntity<ResponseDto> telAuthCheck(@RequestBody @Valid TelAuthCheckRequestDto request) {
        ResponseEntity<ResponseDto> response = authService.telAuthCheck(request);
        return response;
    }

    @PostMapping("sign-up")
    public ResponseEntity<ResponseDto> signUp(@RequestBody @Valid SignUpRequestDto request) {
        ResponseEntity<ResponseDto> response = authService.signUp(request);
        return response;
    }

    @PostMapping("sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("find-tel")
    public ResponseEntity<ResponseDto> findTelNumber(
        @RequestBody @Valid FindTelNumberRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.findTelNumber(requestBody);
        return response;
    }

    @PostMapping("find-id")
    public ResponseEntity<? super FindIdResponseDto> findUserId(
        @RequestBody @Valid FindIdRequestDto requestBody
    ) {
        ResponseEntity<? super FindIdResponseDto> response = authService.findId(requestBody);
        return response;
    }

    @PostMapping("id-tel-check")
    public ResponseEntity<ResponseDto> idTelCheck(
        @RequestBody @Valid IdTelNumberRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.idTelNumberCheck(requestBody);
        return response;
    }

    @PostMapping("find-password")
    public ResponseEntity<? super FindPasswordResponseDto> findPassword(
        @RequestBody @Valid FindPasswordRequestDto requestBody
    ) { 
        ResponseEntity<? super FindPasswordResponseDto> response = authService.findPassword(requestBody);
        return response;
    }
}
