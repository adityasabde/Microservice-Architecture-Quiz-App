package com.QuizApp.quizservice.controller;


import com.QuizApp.quizservice.entity.QuesDTO;
import com.QuizApp.quizservice.entity.QuestionWrapper;
import com.QuizApp.quizservice.entity.Quiz;
import com.QuizApp.quizservice.entity.Response;
import com.QuizApp.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService service;

    @Autowired
    Environment environment;
    @PostMapping("/createquiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuesDTO quesdto){
        return service.createQuiz(quesdto.getCategory() , quesdto.getNumQ() , quesdto.getTitle());
    }

    @GetMapping("/getquiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") long id){
        System.out.println(environment.getProperty("local.server.port"));
        return service.getQuiz(id);
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable("id") long id , @RequestBody List<Response> response){
        return service.getResult(id , response);
    }
}
