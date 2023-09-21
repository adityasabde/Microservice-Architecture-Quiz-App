package com.microservice.questionservice.service;

import com.microservice.questionservice.entity.QuestionWrapper;
import com.microservice.questionservice.entity.Questions;
import com.microservice.questionservice.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuesService {
    public ResponseEntity<List<Questions>> getAllQues();

    public ResponseEntity<Questions>  addquestions(Questions ques);

    ResponseEntity<List<Integer>> generateQuiz(String categoryName, Integer numOfQuestions);

    ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionIDs);

    ResponseEntity<Integer> getScore(List<Response> responseList);
}
