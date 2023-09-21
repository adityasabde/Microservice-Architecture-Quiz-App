package com.QuizApp.quizservice.repository;


import com.QuizApp.quizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDB extends JpaRepository<Quiz, Long> {
}
