package com.QuizApp.quizservice.service;


import com.QuizApp.quizservice.entity.QuestionWrapper;
import com.QuizApp.quizservice.entity.Quiz;
import com.QuizApp.quizservice.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    public ResponseEntity<Quiz> createQuiz(String category , int numQ , String title);

    ResponseEntity<List<QuestionWrapper>> getQuiz(long id);

    ResponseEntity<Integer> getResult(long id, List<Response> response);
}
