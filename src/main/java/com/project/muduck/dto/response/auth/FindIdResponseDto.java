package com.project.muduck.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.muduck.dto.response.ResponseCode;
import com.project.muduck.dto.response.ResponseDto;
import com.project.muduck.dto.response.ResponseMessage;
import com.project.muduck.entity.UserEntity;

import lombok.Getter;

@Getter
public class FindIdResponseDto extends ResponseDto {
    
    private String userId;

    public FindIdResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userEntity.getUserId();
    }

    public static ResponseEntity<FindIdResponseDto> success(UserEntity userEntity) {
        FindIdResponseDto responseBody = new FindIdResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
