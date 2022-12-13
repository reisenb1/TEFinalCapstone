package com.techelevator.model;

import java.util.List;

public class Deck {

    public enum Category {
        GENERAL,
        NATURE,
        COMPUTERS,
        MATH,
        MYTHOLOGY,
        SPORTS,
        BOOKS,
        FILM,
        MUSIC,
        MUSICALS,
        TELEVISION,
        GEOGRAPHY,
        HISTORY,
        ART,
        CELEBRITIES,
        ANIMALS,
        BOARDGAMES,
        VEHICLES
    }

    private int deckId;
    private String deckName;
    private String deckDescription;
    private int creatorId;
    private boolean accessible;

    private Category category;

    public List<Card> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Card> questions) {
        this.questions = questions;
    }

    private List<Card> questions;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }




    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getDeckDescription() {
        return deckDescription;
    }

    public void setDeckDescription(String deckDescription) {
        this.deckDescription = deckDescription;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

}
