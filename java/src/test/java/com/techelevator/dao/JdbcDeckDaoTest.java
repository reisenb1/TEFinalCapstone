package com.techelevator.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.techelevator.model.Deck;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcDeckDaoTest extends BaseDaoTests {

    @Test
    public void getAllMyCreatedDecks() {
        DeckDao deckDao = new JdbcDeckDao(new JdbcTemplate(dataSource));
        List<Deck> decks = deckDao.getAllMyCreatedDecks(2);
        Assert.assertEquals(1, decks.size());
    }

    @Test
    //come back to
    public void getAllMySavedDecks() {
        DeckDao deckDao = new JdbcDeckDao(new JdbcTemplate(dataSource));
        deckDao.getAllMySavedDecks(2);
    }

    @Test
    public void getDeck() {
        DeckDao deckDao = new JdbcDeckDao(new JdbcTemplate(dataSource));
        Deck deck = deckDao.getDeck(3);
        Assert.assertEquals("myDeck3", deck.getDeckName());
    }

    @Test
    public void getDeckWithTagName() {
        DeckDao deckDao = new JdbcDeckDao(new JdbcTemplate(dataSource));
        Deck deck = deckDao.getDeckWithTagName("math");
        Assert.assertEquals("myDeck4", deck.getDeckName());
    }

    @Test
    public void createDeck() {
        DeckDao deckDao = new JdbcDeckDao(new JdbcTemplate(dataSource));
        Deck deck = new Deck();
        deck.setDeckName("TestDeck");
        deck.setDeckDescription("Omg this is a deck");
        deck.setAccessible(false);
        deck.setCreatorId(1);
        Deck result = deckDao.createDeck(deck);
        Assert.assertTrue(result.getDeckId() > 0);
        Assert.assertEquals("TestDeck", result.getDeckName());


    }

    @Test
    public void updateDeck() {
        DeckDao deckDao = new JdbcDeckDao(new JdbcTemplate(dataSource));
    }
}