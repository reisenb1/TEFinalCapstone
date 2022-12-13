package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class ApiFlashcard {

    private String question;
    @JsonProperty("correct_answer")
    private String correctAnswer;
//    @JsonProperty("incorrect_answers")
//    private List<String> incorrectAnswers = null;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

//    public List<String> getIncorrectAnswers() {
//        return incorrectAnswers;
//    }
//
//    public void setIncorrectAnswers(List<String> incorrectAnswers) {
//        this.incorrectAnswers = incorrectAnswers;
//    }
}
