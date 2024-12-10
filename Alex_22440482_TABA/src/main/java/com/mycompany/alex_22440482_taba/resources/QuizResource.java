/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alex_22440482_taba.resources;

import com.mycompany.alex_22440482_taba.services.QuizService;
import com.mycompany.alex_22440482_taba.models.Questions;
import com.mycompany.alex_22440482_taba.models.Quiz;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Alex
 */



//http://localhost:8080/TABA/api/quizzes is the full path to view all quizzes 
//  /quizzes/1    to view the first ID quiz , /quizzes/(ID number) to view new added ones such as 2 or 3 
// /quizzes/(id number)/submit  for submitting student scores




@Path("/quizzes")
public class QuizResource {
    
    //linking the quiz service class here
    private final QuizService quizService = new QuizService();

    
    
    
    //api key verify
    private boolean isValidTeacher(String apiKey) {
        return "teacher-key".equals(apiKey); // Simple API key validation
    }

    
    
    
    //Get all quizzes //in JSON format 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQuizzes() {
        return Response.ok(quizService.getAllQuizzes()).build();
    }

    
    
    
    //Get all quiz details by ID
    @GET
    @Path("/{quizID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuiz(@PathParam("quizID") int quizID) {
        
        //Get id 
        Quiz quiz = quizService.getQuizById(quizID);
        
        //if wrong input or not in use 
        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Quiz not found").build();
        }
        return Response.ok(quiz).build();
    }

    
    
    
    
    //Create/Post a new quiz (teacher only)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createQuiz(Quiz quiz, @HeaderParam("API-Key") String apiKey) {
        
        //if no api key input 
        if (!isValidTeacher(apiKey)) {
            return Response.status(Response.Status.FORBIDDEN).entity("You need an api key for access").build();
        }

        //if guiz ID is null or already created 
        if (quizService.getQuizById(quiz.getQuizID()) != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("This ID already exists").build();
        }
        
        //if id is not taken, and format is correct 
        quizService.addQuiz(quiz);
        return Response.status(Response.Status.CREATED).entity("Quiz created successfully").build();
    }

    
    
    
    
    //Update/Put a quiz (teacher only)
    @PUT
    @Path("/{quizID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateQuiz(@PathParam("quizID") int quizID, Quiz updatedQuiz, @HeaderParam("API-Key") String apiKey) {
        
        //if no api key 
        if (!isValidTeacher(apiKey)) {
            return Response.status(Response.Status.FORBIDDEN).entity("You need an  api key for access").build();
        }

        //wrong id 
        Quiz existingQuiz = quizService.getQuizById(quizID);
        if (existingQuiz == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("quiz could not be found").build();
        }

        //update the title or questions of the quiz
        existingQuiz.setTitle(updatedQuiz.getTitle());
        existingQuiz.setQuestions(updatedQuiz.getQuestions());

        //returning a success message with the updated quiz input
        return Response.ok(existingQuiz).entity("Quiz updated successfully").build();
    }

    
    
    
    
    //Post a question to a quiz (teacher only) //json format 
    @POST
    @Path("/{quizID}/questions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addQuestion(@PathParam("quizID") int quizID, Questions question, @HeaderParam("API-Key") String apiKey) {
        
        //if no key input then
        if (!isValidTeacher(apiKey)) {
            return Response.status(Response.Status.FORBIDDEN).entity("").build();
        }

        boolean success = quizService.addQuestionToQuiz(quizID, question);
        if (!success) {
            return Response.status(Response.Status.NOT_FOUND).entity("Quiz not found").build();
        }
        return Response.status(Response.Status.OK).entity("Question added successfully").build();
    }

    
    
    
    
    //Delete a quiz (teacher only)
    @DELETE
    @Path("/{quizID}")
    //HeaderParam for Postman needs to be 
    public Response deleteQuiz(@PathParam("quizID") int quizID, @HeaderParam("API-Key") String apiKey) {
        
        //If no key is put in 
        if (!isValidTeacher(apiKey)) {
            return Response.status(Response.Status.FORBIDDEN).entity("You need an API key for access").build();
        }

        //No quizzes from id found
        if (quizService.getQuizById(quizID) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Quiz not found").build();
        }
        
        //if quiz id is found then its succesfull 
        quizService.deleteQuiz(quizID);
        return Response.status(Response.Status.OK).entity("Quiz deleted successfully").build();
        
    }

    
    
    
    //Submit answers for the quiz (student only) //json format 
    @POST
    @Path("/{quizID}/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitQuiz(@PathParam("quizID") int quizID, List<String> studentAnswers) {
        Quiz quiz = quizService.getQuizById(quizID);
        
        //if quiz is null
        if (quiz == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Quiz could not be found").build();
        }

        //showcase score
        int score = quizService.calculateScore(quiz, studentAnswers);
        return Response.ok("Your score is: " + score).build();
    }
    
} //End
