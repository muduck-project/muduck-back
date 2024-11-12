package com.project.muduck.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.muduck.dto.request.auth.IdCheckRequestDto;
import com.project.muduck.dto.response.ResponseDto;
// import com.project.muduck.provider.JwtProvider;
import com.project.muduck.provider.SmsProvider;
import com.project.muduck.repository.UserRepository;
import com.project.muduck.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService  {

    private final SmsProvider smsProvider;
    // private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

        String userId = dto.getUserId();

        try {

            boolean isExisted = userRepository.existsById(userId);
            if(isExisted)
                return ResponseDto.duplicatedUserId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }
    
}
