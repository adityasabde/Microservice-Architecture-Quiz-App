package com.microservice.questionservice.servieop;

import com.microservice.questionservice.entity.QuestionWrapper;
import com.microservice.questionservice.entity.Questions;
import com.microservice.questionservice.entity.Response;
import com.microservice.questionservice.repository.QuestionRepo;
import com.microservice.questionservice.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionServiceOp implements QuesService {

    @Autowired
    QuestionRepo repo;
    @Override
    public ResponseEntity<List<Questions>> getAllQues() {
        try{
            return new ResponseEntity<>(repo.findAll() ,HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Questions> addquestions(Questions ques) {
        try {
            return new ResponseEntity<>(repo.save(ques), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new Questions() , HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Integer>> generateQuiz(String categoryName, Integer numOfQuestions) {
        try{
            List<Integer> listOfQuestion = repo.findRandomQuestionByCategory(categoryName , numOfQuestions);
            return new ResponseEntity<>(listOfQuestion , HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionIDs) {
        try{
            List<QuestionWrapper> questionwrapperlist = new ArrayList<>();
            List<Questions> questionlist = new ArrayList<>();

            for(Integer id : questionIDs){
                questionlist.add(repo.findById((long)id).get());
            }
            for(Questions que : questionlist){
                QuestionWrapper wrapper = new QuestionWrapper();
                wrapper.setQuestion(que.getQuestion());
                wrapper.setId((int)que.getId());
                wrapper.setOption1(que.getOption1());
                wrapper.setOption2(que.getOption2());
                wrapper.setOption3(que.getOption3());
                wrapper.setOption4(que.getOption4());
                questionwrapperlist.add(wrapper);
            }
            return new ResponseEntity<>(questionwrapperlist ,  HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responseList) {
       try{
           int right = 0;
           for(Response res : responseList){
               Questions ques = repo.findById(res.getId()).get();
               if(res.getResponse() == ques.getAnswer()){
                   right++;
               }
           }
           return new ResponseEntity<>(right , HttpStatus.OK);
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(0 , HttpStatus.BAD_REQUEST);
    }
}
