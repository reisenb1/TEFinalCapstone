package com.techelevator.dao;


import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
 public class JdbcCardDao implements CardDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Card> getAllCards(int deckId) {
        List<Card> allCards = new ArrayList<>();
        String sql = "SELECT * FROM cards WHERE deck_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        while(results.next()) {
            allCards.add(mapRowToCard(results));
        }
        return allCards;
    }

    @Override
    public Card getCard(int cardId) {
        String sql = "SELECT * FROM cards WHERE card_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,cardId);
        if (rowSet.next()) {
            Card card = mapRowToCard(rowSet);
            return card;
        } else {
            return null;
        }
    }

    @Override
    public Card createCard(Card card) {
        String sql = "INSERT INTO cards(deck_id, front, back)\n" +
                     "VALUES(?, ?, ?) RETURNING card_id;";
        Integer cardId = jdbcTemplate.queryForObject(sql, Integer.class, card.getDeckId(),card.getFront(),card.getBack());
        card.setCardId(cardId);
        return card;
    }

    @Override
    public boolean updateCard(Card card) {
        String sql = "UPDATE cards SET deck_id = ?, front = ?, back = ?" +
                     "WHERE card_id = ?;";
        int count = jdbcTemplate.update(sql, card.getDeckId(), card.getFront(), card.getBack(), card.getCardId());
        return count == 1;
    }

    @Override
    public boolean deleteCard(int cardId) {
        String sql = "DELETE FROM cards WHERE card_id = ?;";
        int count = jdbcTemplate.update(sql,cardId);
        return count == 1;
    }


    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setDeckId(rowSet.getInt("deck_id"));
        card.setFront(rowSet.getString("front"));
        card.setBack(rowSet.getString("back"));
        return card;
    }
}
