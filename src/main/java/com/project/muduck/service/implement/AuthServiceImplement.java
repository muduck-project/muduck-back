package com.project.muduck.service.implement;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.muduck.common.util.CreateNumber;
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
import com.project.muduck.entity.TelAuthEntity;
import com.project.muduck.entity.UserEntity;
import com.project.muduck.provider.JwtProvider;
// import com.project.muduck.provider.JwtProvider;
import com.project.muduck.provider.SmsProvider;
import com.project.muduck.repository.TelAuthRepository;
import com.project.muduck.repository.UserRepository;
import com.project.muduck.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService  {

    private final SmsProvider smsProvider;
    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;
    private final TelAuthRepository telAuthRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @Override
    public ResponseEntity<ResponseDto> telAuth(TelAuthRequsetDto dto) {

        String telNumber = dto.getTelNumber();

        try {

            boolean isExisted = userRepository.existsByTelNumber(telNumber);
            if(isExisted)
                return ResponseDto.duplicatedTelNumber(); 

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = new CreateNumber().generateAuthNumber();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if(!isSendSuccess)
            return ResponseDto.messageSendFail();

            try {

                TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
                telAuthRepository.save(telAuthEntity);

            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }

            return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
        
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        
        try {

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if(!isMatched)
                return ResponseDto.telAuthFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {

            boolean isExistedUserId = userRepository.existsById(userId);
            if(isExistedUserId) return ResponseDto.duplicatedUserId();

            boolean isExistedTelNumber = userRepository.existsByTelNumber(telNumber);
            if(isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if(!isMatched) return ResponseDto.telAuthFail();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String userId = dto.getUserId();
        String password = dto.getPassword();

        String accessToken = null;

        try {
            
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null)
                return ResponseDto.signInFail();

            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched)
                return ResponseDto.signInFail();

            accessToken = jwtProvider.create(userId);
            if(accessToken == null)
                return ResponseDto.tokenCreateFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(accessToken);
    }

    
    @Override
    public ResponseEntity<ResponseDto> findTelNumber(FindTelNumberRequestDto dto) {

        String telNumber = dto.getTelNumber();

        try {

            boolean isMatched = userRepository.existsByTelNumber(telNumber);
            if (!isMatched)
                return ResponseDto.noExistTelNumber();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = new CreateNumber().generateAuthNumber();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if (!isSendSuccess)
            return ResponseDto.messageSendFail();

        try {

            TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
            telAuthRepository.save(telAuthEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super FindIdResponseDto> findId(FindIdRequestDto dto) {
        
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        UserEntity userEntity = null;

        try {

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if(!isMatched)
                return ResponseDto.telAuthFail();

            userEntity = userRepository.findByTelNumber(telNumber);
            if(userEntity == null)
                return ResponseDto.noExistTelNumber();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return FindIdResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> idTelNumberCheck(IdTelNumberRequestDto dto) {

        String userId = dto.getUserId();
        String telNumber = dto.getTelNumber();

        try {

            boolean isMatched = userRepository.existsByUserIdAndTelNumber(userId, telNumber);
            if (!isMatched)
                return ResponseDto.noExistUserIdAndTelNumber();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = new CreateNumber().generateAuthNumber();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if (!isSendSuccess)
            return ResponseDto.messageSendFail();

        try {

            TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
            telAuthRepository.save(telAuthEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    private String generateTempPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Override
    public ResponseEntity<? super FindPasswordResponseDto> findPassword(FindPasswordRequestDto dto) {

        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        String tempPassword = null;

        UserEntity userEntity = null;

        try {

            boolean isMatched = telAuthRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if (!isMatched)
                return ResponseDto.telAuthFail();

            userEntity = userRepository.findByTelNumber(dto.getTelNumber());
            if (userEntity == null)
                return ResponseDto.noExistTelNumber();

            tempPassword = generateTempPassword();
            String encodedTempPassword = passwordEncoder.encode(tempPassword);

            userEntity.setPassword(encodedTempPassword);
            userRepository.save(userEntity);

            boolean isSendSuccess = smsProvider.sendMessage(telNumber, tempPassword);
            if (!isSendSuccess)
                return ResponseDto.messageSendFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return FindPasswordResponseDto.success(tempPassword);
    }

}
