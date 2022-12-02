package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

public interface CardDao {

    List<Card> getAllCards(int deckId);

    Card getCard(int cardId);

    Card createCard(Card card);

    Card updateCard(Card card);

    boolean deleteCard(int cardId);

}
