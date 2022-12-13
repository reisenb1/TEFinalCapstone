package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;
import java.util.Map;

@Generated("jsonschema2pojo")
public class ApiDeck {

    // Map Category to external category code
    public static final Map<Deck.Category, Integer> CATEGORY_CODES = Map.ofEntries(
            Map.entry(Deck.Category.GENERAL , 9),
            Map.entry(Deck.Category.NATURE, 17),
            Map.entry(Deck.Category.COMPUTERS, 18),
            Map.entry(Deck.Category.MATH, 19),
            Map.entry(Deck.Category.MYTHOLOGY, 20),
            Map.entry(Deck.Category.SPORTS, 21),
            Map.entry(Deck.Category.BOOKS, 10),
            Map.entry(Deck.Category.FILM, 11),
            Map.entry(Deck.Category.MUSIC, 12),
            Map.entry(Deck.Category.BOARDGAMES, 16),
            Map.entry(Deck.Category.TELEVISION, 14),
            Map.entry(Deck.Category.GEOGRAPHY, 22),
            Map.entry(Deck.Category.HISTORY, 23),
            Map.entry(Deck.Category.ART, 22),
            Map.entry(Deck.Category.CELEBRITIES, 26),
            Map.entry(Deck.Category.ANIMALS, 27),
            Map.entry(Deck.Category.VEHICLES, 28)
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
