package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Deck> getAllMyCreatedDecks(int userId) {
        List<Deck> allDecks = new ArrayList<>();
        String sql = "SELECT * FROM decks WHERE creator_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            allDecks.add(mapRowToDeck(results));
        }
        return allDecks;
    }

    @Override
    public List<Deck> getAllMySavedDecks(int userId) {
        List<Deck> allDecks = new ArrayList<>();
        String sql = "SELECT * FROM decks\n" +
                "JOIN user_deck ON user_deck.deck_id= decks.deck_id\n" +
                "WHERE user_id = ? AND creator_id != ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next()) {
            allDecks.add(mapRowToDeck(results));
        }
        return allDecks;
    }

    @Override
    public Deck getDeck(int deckId) {
        String sql = "SELECT * FROM decks WHERE deck_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, deckId);
        if (rowSet.next()) {
            Deck deck = mapRowToDeck(rowSet);
            return deck;
        } else {
            return null;
        }
    }

    @Override
    public Deck getDeckWithTagName(String tagName) {
        String sql = "SELECT * FROM decks\n" +
                "JOIN deck_tag ON deck_tag.deck_id = decks.deck_id\n" +
                "JOIN tags ON tags.tag_id = deck_tag.tag_id\n" +
                "WHERE tag_name = ?;\n";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, tagName);
        if (rowSet.next()) {
            Deck deck = mapRowToDeck(rowSet);
            return deck;
        } else {
            return null;
        }
    }

    @Override
    public Deck createDeck(Deck deck) {
        String sql = "INSERT INTO decks(deck_name, deck_description, accessible, creator_id)\n" +
                "VALUES(?, ?, ?, ?) RETURNING deck_id;";
        Integer deckId = jdbcTemplate.queryForObject(sql, Integer.class, deck.getDeckName(), deck.getDeckDescription(), deck.isAccessible(), deck.getCreatorId());
        deck.setDeckId(deckId);
        sql = "INSERT INTO user_deck(deck_id,user_id) VALUES (?,?);";
        jdbcTemplate.update(sql, deck.getDeckId(), deck.getCreatorId());
        return deck;
    }

    @Override
    public boolean updateDeck(Deck deck, int deckId) {
        String sql = "UPDATE decks SET deck_name = ?, deck_description = ?, accessible = ?, creator_id = ?" +
                "WHERE deck_id = ?;";
        int count = jdbcTemplate.update(sql, deck.getDeckName(), deck.getDeckDescription(), deck.isAccessible(), deck.getCreatorId(), deckId);
        return count == 1;
    }

//    @Override
//    public List<Deck> getAllPublicDecks() {
//        String sql = "SELECT * FROM decks WHERE accessible = true;\n";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        List<Deck> publicDecks = new ArrayList<>();
//        while(results.next()) {
//            publicDecks.add(mapRowToDeck(results));
//        }
//        return publicDecks;
//    }

    @Override
    public List<Deck> getAllDecks() {
        String sql = "SELECT * FROM decks;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Deck> allDecks = new ArrayList<>();
        while (results.next()) {
            allDecks.add(mapRowToDeck(results));
        }
        return allDecks;
    }


    @Override
    public List<Deck> searchByName(String search) {
        String sql = "SELECT * FROM decks WHERE deck_name iLIKE ? OR deck_description iLIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + search + "%", "%" + search + "%");
        List<Deck> searchedDecks = new ArrayList<>();
        while (results.next()) {
            searchedDecks.add(mapRowToDeck(results));
        }
        return searchedDecks;
    }

    @Override
    public List<Deck> getMySearchDecks(int creatorId, String search) {
        String sql = "SELECT * FROM decks WHERE creator_id = ? AND (deck_name iLIKE ? OR deck_description iLIKE ?);\n";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creatorId, "%" + search + "%", "%" + search + "%" );
        List<Deck> mySearchedDecks = new ArrayList<>();
        while(results.next()) {
            mySearchedDecks.add(mapRowToDeck(results));
        }
        return mySearchedDecks;
    }

//    @Override
//    public boolean deleteDeck(int deckId) {
//        String sql = "DELETE FROM cards WHERE deck_id = ?;";
//        jdbcTemplate.update(sql,deckId);
//        sql = "DELETE FROM deck_tag WHERE deck_id = ?;";
//        jdbcTemplate.update(sql,deckId);
//        sql = "DELETE FROM user_deck WHERE deck_id = ?;";
//        jdbcTemplate.update(sql,deckId);
//        sql = "DELETE FROM decks WHERE deck_id = ?;";
//        int count = jdbcTemplate.update(sql,deckId);
//        return count == 1;
//    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setDeckName(rowSet.getString("deck_name"));
        deck.setDeckDescription(rowSet.getString("deck_description"));
        deck.setAccessible(rowSet.getBoolean("accessible"));
        deck.setCreatorId(rowSet.getInt("creator_id"));
        return deck;
    }

}
