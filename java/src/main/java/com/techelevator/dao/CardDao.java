package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Deck;

import java.util.List;

public interface CardDao {

    List<Card> getAllCards(int deckId);

    Card getCard(int cardId);

    Card createCard(Card card);

    boolean updateCard(Card card, int cardId);

    boolean deleteCard(int cardId);



}
