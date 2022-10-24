package com.example.TeamTracker.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PARTIAL_CONTENT, reason = "Username already taken")
public class UsernameAlreadyTakenException extends RuntimeException{
}
