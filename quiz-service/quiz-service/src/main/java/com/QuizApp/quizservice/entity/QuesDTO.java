package com.QuizApp.quizservice.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuesDTO {
   private String category;
    private int numQ;
    private String title;
}
