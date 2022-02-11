package com.devtyagi.maalgaadi.config;

import com.devtyagi.maalgaadi.dto.response.ErrorResponseDTO;
import com.devtyagi.maalgaadi.exception.BaseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({ BaseException.class })
    public ResponseEntity<ErrorResponseDTO> exceptionHandler(final BaseException exception) {
        val response = ErrorResponseDTO.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response, exception.getResponseHttpStatus());
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    public ResponseEntity<ErrorResponseDTO> userNotFoundExceptionHandler(final UsernameNotFoundException usernameNotFoundException) {
        val response = ErrorResponseDTO.builder()
                .message(usernameNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ExpiredJwtException.class })
    public ResponseEntity<ErrorResponseDTO> expiredJwtExceptionHandler(final ExpiredJwtException expiredJwtException) {
        val response = ErrorResponseDTO.builder()
                .message("token_expired")
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ SignatureException.class })
    public ResponseEntity<ErrorResponseDTO> invalidTokenException(final SignatureException signatureException) {
        val response = ErrorResponseDTO.builder()
                .message(signatureException.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ MalformedJwtException.class })
    public ResponseEntity<ErrorResponseDTO> malformedJwtExceptionHandler(final MalformedJwtException malformedJwtException) {
        val response = ErrorResponseDTO.builder()
                .message("invalid_token")
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}