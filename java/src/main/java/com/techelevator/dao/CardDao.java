package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

public interface CardDao {

    List<Card> getAllCards(int deckId);

    Card getCard(int cardId);

    Card createCard(Card card);

    boolean updateCard(Card card);

    boolean deleteCard(int cardId);

}
