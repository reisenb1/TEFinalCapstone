package com.techelevator.dao;


import com.techelevator.model.Card;

import java.util.List;

public class JdbcCardDao implements CardDao {
    @Override
    public List<Card> getAllCards(int deckId) {
        return null;
    }

    @Override
    public Card getCard(int cardId) {
        return null;
    }

    @Override
    public Card createCard(Card card) {
        return null;
    }

    @Override
    public Card updateCard(Card card) {
        return null;
    }

    @Override
    public boolean deleteCard(int cardId) {
        return false;
    }
}
