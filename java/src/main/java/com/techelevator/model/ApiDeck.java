package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;

@Generated("jsonschema2pojo")
public class ApiDeck {

    // Map Category to external category code
    public static final Map<Deck.Category, Integer> CATEGORY_CODES = Map.of(
            Deck.Category.GENERAL , 9,
            Deck.Category.NATURE, 17,
            Deck.Category.COMPUTERS, 18,
            Deck.Category.MATH, 19,
            Deck.Category.MYTHOLOGY, 20,
            Deck.Category.SPORTS, 21
    );

    @JsonProperty("results")
    private List<ApiFlashcard> questions = null;

    public List<ApiFlashcard> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ApiFlashcard> questions) {
        this.questions = questions;
    }
}
