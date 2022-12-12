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

    List<Deck> searchByName(String search);

//    List<Deck> getAllPublicDecks();

    List<Deck> getAllDecks();

    List<Deck> getMySearchDecks(int creatorId, String search);

//    boolean deleteDeck(int deckId);



}
