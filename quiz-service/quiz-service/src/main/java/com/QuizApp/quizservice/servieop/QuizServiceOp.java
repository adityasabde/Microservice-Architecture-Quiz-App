package com.QuizApp.quizservice.servieop;


import com.QuizApp.quizservice.entity.QuestionWrapper;
import com.QuizApp.quizservice.entity.Quiz;
import com.QuizApp.quizservice.entity.Response;
import com.QuizApp.quizservice.feign.QuizInterface;
import com.QuizApp.quizservice.repository.QuizDB;
import com.QuizApp.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceOp implements QuizService {
    @Autowired
    QuizDB repo;
    @Autowired
    QuizInterface qservice;
    @Override
    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {
      try{
          List<Integer> ListOfQuestion = qservice.generateQuiz(category , numQ).getBody();
          if (ListOfQuestion != null && !ListOfQuestion.isEmpty()) {
              Quiz quiz = new Quiz();
              quiz.setQuelist(ListOfQuestion);
              quiz.setTitle(title);
              Quiz savedQuiz = repo.save(quiz);
              return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
          }
          else{
              System.out.println("arraylist is empty");
          }
      }
      catch (Exception e){
          e.printStackTrace();
      }
      return new ResponseEntity<>(new Quiz() , HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuiz(long id) {
        try{
            Quiz dataFromQuizDB = repo.findById(id).get();
            List<Integer> listofQues = dataFromQuizDB.getQuelist();
            ResponseEntity<List<QuestionWrapper>> ListOfClientQuestion = qservice.getQuestions(listofQues);

            return ListOfClientQuestion;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Integer> getResult(long id, List<Response> response) {
        Integer score = qservice.getScore(response).getBody();
        return new ResponseEntity<>(score , HttpStatus.OK);
    }
}
