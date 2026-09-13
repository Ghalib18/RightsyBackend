package com.Rightsy.demo.error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExpectionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex){
        ApiError apiError=new ApiError("username not found with username :"+ ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex){
        ApiError apiError=new ApiError("Authentication Failed: "+ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException ex){
        ApiError apiError=new ApiError("Invalid JWT Token : "+ex.getMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex){
        ApiError apiError=new ApiError("Access Denied: " +ex.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex){
        ApiError apiError=new ApiError("An unexpected error Occurred :"+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError,apiError.getStatusCode());
    }

}
