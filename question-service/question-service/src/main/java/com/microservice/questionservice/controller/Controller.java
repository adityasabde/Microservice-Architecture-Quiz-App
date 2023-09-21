package com.microservice.questionservice.controller;

import com.microservice.questionservice.entity.QuestionWrapper;
import com.microservice.questionservice.entity.Questions;
import com.microservice.questionservice.entity.Response;
import com.microservice.questionservice.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class Controller
{
    @Autowired
    QuesService service;

    @Autowired
    Environment environment;
//    getting all questions from question DB
    @GetMapping("/allquestion")
    public ResponseEntity<List<Questions>> getAllQues(){
        return  service.getAllQues() ;
    }


//    adding new question to the DB
    @PostMapping("/addquestion")
    public ResponseEntity<Questions> addquestion(@RequestBody Questions q){
        return service.addquestions(q);
    }


//    generating the new Quiz by returning the list of question id
    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuiz
        (@RequestParam String categoryName , @RequestParam Integer numOfQuestions){
        return service.generateQuiz(categoryName , numOfQuestions);
    }

    @PostMapping("/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIDs){
        System.out.println(environment.getProperty("local.server.port"));
        return service.getQuestions(questionIDs);
    }

    @PostMapping("/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responseList){
        return service.getScore(responseList);
    }
}
