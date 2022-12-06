package com.techelevator.model;

public class StudySession {

    private int studySessionId;
    private int userId;
    private int deckId;
    private int numberOfCards;
    private int numberCorrect;
    private int numberGreen;
    private int numberYellow;
    private int numberRed;

    public int getStudySessionId() {
        return studySessionId;
    }

    public void setStudySessionId(int studySessionId) {
        this.studySessionId = studySessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberGreen() {
        return numberGreen;
    }

    public void setNumberGreen(int numberGreen) {
        this.numberGreen = numberGreen;
    }

    public int getNumberYellow() {
        return numberYellow;
    }

    public void setNumberYellow(int numberYellow) {
        this.numberYellow = numberYellow;
    }

    public int getNumberRed() {
        return numberRed;
    }

    public void setNumberRed(int numberRed) {
        this.numberRed = numberRed;
    }
}
