package com.project.muduck.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.muduck.dto.response.ResponseCode;
import com.project.muduck.dto.response.ResponseDto;
import com.project.muduck.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class FindPasswordResponseDto extends ResponseDto {

    private String password;

    public FindPasswordResponseDto(String password) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.password = password;
    }

    public static ResponseEntity<FindPasswordResponseDto> success(String password) {
        FindPasswordResponseDto responseBody = new FindPasswordResponseDto(password);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
