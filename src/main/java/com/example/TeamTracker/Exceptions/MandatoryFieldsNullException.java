package com.example.TeamTracker.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PARTIAL_CONTENT, reason = "Not all attributes filled in a correct way")
public class MandatoryFieldsNullException extends RuntimeException {
}
