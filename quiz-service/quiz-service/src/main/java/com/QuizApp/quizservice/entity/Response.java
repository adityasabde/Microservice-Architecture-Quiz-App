package com.QuizApp.quizservice.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private  long id;
    private long response;
}
