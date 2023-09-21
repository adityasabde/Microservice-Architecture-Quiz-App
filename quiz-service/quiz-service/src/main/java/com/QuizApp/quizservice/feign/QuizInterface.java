package com.QuizApp.quizservice.feign;

import com.QuizApp.quizservice.entity.QuestionWrapper;
import com.QuizApp.quizservice.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @PostMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuiz
    (@RequestParam String categoryName , @RequestParam Integer numOfQuestions);

    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIDs);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responseList);
}
