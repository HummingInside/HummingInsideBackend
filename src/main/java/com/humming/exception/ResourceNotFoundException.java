package com.humming.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {}
