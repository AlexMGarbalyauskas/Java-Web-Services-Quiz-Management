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


//Java class questions to store question texts answers and choices
public class Questions {
    
    //Text that will appear on either json or xml 
    private String questionText;
    //Correct answer for the question
    private String correctAnswer;  
    //Choices for the multiple-choice question
    private List<String> choices;  

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }  
}
