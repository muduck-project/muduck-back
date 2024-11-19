package com.project.muduck.service;

import org.springframework.http.ResponseEntity;

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

public interface AuthService {
    
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> telAuth(TelAuthRequsetDto dto);
    ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<ResponseDto> findTelNumber(FindTelNumberRequestDto dto);
    ResponseEntity<? super FindIdResponseDto> findId(FindIdRequestDto dto);
    ResponseEntity<ResponseDto> idTelNumberCheck(IdTelNumberRequestDto dto);
    ResponseEntity<? super FindPasswordResponseDto> findPassword(FindPasswordRequestDto dto);
}
