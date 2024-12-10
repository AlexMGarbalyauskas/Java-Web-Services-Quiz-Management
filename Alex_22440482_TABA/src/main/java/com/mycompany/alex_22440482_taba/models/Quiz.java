/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alex_22440482_taba.models;

import java.util.List;

/**
 *
 * @author Alex
 */


//Java Class to store the quizzes ids and titles to be displayed 
public class Quiz {
    
    //quiz id number 
    private int quizID;
    //title of the the quiz
    private String title; 
    //the questions list
    private List<Questions> questions;
    
    //Getters and Setters
    public int getQuizID() { 
        return quizID; 
    }
    
    public void setQuizID(int quizID) { 
        this.quizID = quizID; 
    }

    public String getTitle() { 
        return title; 
    }
    
    public void setTitle(String title) {
        this.title = title; 
    }

    public List<Questions> getQuestions() { 
        return questions; 
    }
    
    public void setQuestions(List<Questions> questions) { 
        this.questions = questions; 
    }   
    
}
