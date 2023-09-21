package com.microservice.questionservice.repository;


import com.microservice.questionservice.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Long> {
    public List<Questions> findByCategory(String category);

    @Query(nativeQuery = true, value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numq")
    public List<Integer> findRandomQuestionByCategory(@Param("category") String category, @Param("numq") int numq);

}
