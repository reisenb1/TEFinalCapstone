package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcDeckDao implements DeckDao{

    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Deck> getAllMyCreatedDecks(int userId) {
        List<Deck> allDecks = new ArrayList<>();
        String sql = "SELECT * FROM decks WHERE creator_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            allDecks.add(mapRowToDeck(results));
        }
        return allDecks;
    }

    @Override
    public List<Deck> getAllMySavedDecks(int userId) {
        List<Deck> allDecks = new ArrayList<>();
        String sql = "SELECT * FROM decks WHERE creator_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            allDecks.add(mapRowToDeck(results));
        }
        return allDecks;
    }

    @Override
    public Deck getDeck(int deckId) {
        return null;
    }

    @Override
    public Deck createDeck(Deck deck) {
        String sql = "INSERT INTO decks(deck_name, deck_description, accessible, creator_id)\n" +
                "VALUES(?, ?, ?, ?) RETURNING deck_id;";
        Integer deckId = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckId());
        deck.setDeckId(deckId);
        return deck;
    }

    @Override
    public Deck updateDeck(Deck deck) {
        return null;
    }

    @Override
    public boolean deleteDeck(int deckId) {
        return false;
    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setDeckName(rowSet.getString("deck_name"));
        deck.setDeckDescription(rowSet.getString("deck_description"));
        deck.setAccessible(rowSet.getBoolean("accessible"));
        return deck;
    }

}
