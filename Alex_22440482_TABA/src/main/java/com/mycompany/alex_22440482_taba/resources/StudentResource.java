/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alex_22440482_taba.resources;

import com.mycompany.alex_22440482_taba.models.Questions;
import com.mycompany.alex_22440482_taba.models.Quiz;
import com.mycompany.alex_22440482_taba.services.QuizService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

/**
 *
 * @author Alex
 */


// /quizzes/(id number)/submit  for submitting student scores



public class StudentResource {
    
    //linking quiz service 
    private final QuizService quizService = new QuizService();

    
    
 
    @GET
    @Path("/quizzes") //json format 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableQuizzes() {
        return Response.ok(quizService.getAllQuizzes()).build();
    }
    
    
    
    @POST
    @Path("/quizzes/{quizId}/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitAnswers(@PathParam("quizId") int quizId, Map<Integer, String> answers) {
        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Quiz was not found").build();
        }

        // score for answers
        int score = calculateScore(quiz, answers);

        // Return score wrapped in a response with a message
        return Response.ok(Map.of("message", "Your score is: " + score)).build();
    }


    
    
    
    
    //calculating scores
    private int calculateScore(Quiz quiz, Map<Integer, String> answers) {
        int score = 0;
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Questions question = quiz.getQuestions().get(i);
            if (answers.get(i).equals(question.getCorrectAnswer())) {
                score++;
            }
        }
        return score;
    }
    
} //End
