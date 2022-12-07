package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDao {

    List<Deck> getAllMyCreatedDecks(int userId);

    List<Deck> getAllMySavedDecks(int userId);

    Deck getDeck(int deckId);

    Deck getDeckWithTagName(String tagName);

    Deck createDeck(Deck deck);

    boolean updateDeck(Deck deck, int deckId);


//    boolean deleteDeck(int deckId);



}
