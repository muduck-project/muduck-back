package com.project.muduck.service;

import org.springframework.http.ResponseEntity;

import com.project.muduck.dto.request.auth.IdCheckRequestDto;
import com.project.muduck.dto.response.ResponseDto;

public interface AuthService {
    
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
}
