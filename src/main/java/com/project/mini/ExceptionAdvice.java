package com.project.mini;

import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object ttpRequestMethodNotSupportedException(Exception e) {
        System.err.println(e.getClass());
        return "redirect:/";
    }




}