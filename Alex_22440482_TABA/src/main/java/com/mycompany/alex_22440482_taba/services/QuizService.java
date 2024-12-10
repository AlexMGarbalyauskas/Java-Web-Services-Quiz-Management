/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alex_22440482_taba.services;

import com.mycompany.alex_22440482_taba.models.Questions;
import com.mycompany.alex_22440482_taba.models.Quiz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */


//Java service class for quizzes 
public class QuizService {
    
    //Map 
    private static final Map<Integer, Quiz> quizzes = new HashMap<>();
    
    //static int to track the next ID 
    private static int nextQuizID = 1; 

    
    
    
    //Creating a first example quiz of what the teachers can add 
    static {
        
        //one quiz with questions created 
        Quiz quiz = new Quiz();
        
        //quiz set up to auto 
        quiz.setQuizID(nextQuizID++);
        quiz.setTitle("1st Quiz");

        //Geography quiz about Japan 
        Questions question1 = new Questions();
        question1.setQuestionText("What is the capital of Japan?");
        question1.setChoices(List.of("Tokyo", "Osaka", "Hong Kong", "Seoul"));
        question1.setCorrectAnswer("Tokyo");

        //setting the questions into the list 
        quiz.setQuestions(new ArrayList<>(List.of(question1)));
        quizzes.put(quiz.getQuizID(), quiz);
    }
    
    
    
    
    
    //to get all the quizes and for it to be stored 
    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }

    
    
    
    //to get all the quiz id 
    public Quiz getQuizById(int quizId) {
        return quizzes.get(quizId);
    }

    
    
    
    //adding new quizzes to appear after posting 
    public void addQuiz(Quiz quiz) {
        // Set quizID to the next available ID
        quiz.setQuizID(nextQuizID++);
        quizzes.put(quiz.getQuizID(), quiz);
    }
    
    
    
    
    
    //adding new questions to quiz after @put 
    public boolean addQuestionToQuiz(int quizId, Questions question) {
        Quiz quiz = quizzes.get(quizId);
        if (quiz != null) {
            quiz.getQuestions().add(question);
            return true;
        }
        return false;
    }

    
    
    
    //to delete the quizzes , from id only  
    public void deleteQuiz(int quizId) {
        quizzes.remove(quizId);
    }

    
    
    

    //calcualte the score of student 
    public int calculateScore(Quiz quiz, List<String> studentAnswers) {
        int score = 0;
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            if (quiz.getQuestions().get(i).getCorrectAnswer().equals(studentAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }
    
} //End
