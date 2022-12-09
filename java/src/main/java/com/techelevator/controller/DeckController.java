package com.techelevator.controller;


import com.techelevator.dao.DeckDao;
import com.techelevator.dao.JdbcDeckDao;
import com.techelevator.model.Deck;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DeckController {

    private DeckDao deckDao;

    public DeckController(DeckDao deckDao) {
        this.deckDao = deckDao;
    }

    @RequestMapping(path = "/{userId}/decks", method = RequestMethod.GET)
    public List<Deck> getAllDecksFromUser(@PathVariable int userId,  @RequestParam(value = "saved", required = false) Boolean saved) {
        if (saved){
            return deckDao.getAllMySavedDecks(userId);
        } else {
            return deckDao.getAllMyCreatedDecks(userId);
        }
    }

    @RequestMapping(path = "/decks/search", method = RequestMethod.GET)
    public List<Deck> getAllSearchedDecks(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            return deckDao.searchByName(search);
        } else {
            return deckDao.getAllDecks();
        }
    }

    @RequestMapping(path= "/decks", method = RequestMethod.GET)
    public List<Deck> getAllDecks() {
        return deckDao.getAllDecks();
    }

   @RequestMapping(path = "{userId}/decks/search", method = RequestMethod.GET)
    public List<Deck> getMySearchedDecks(@PathVariable int userId, @RequestParam(value = "search", required = false) String search) {
       if (search != null) {
           return deckDao.getMySearchDecks(userId, search);
       } else {
           return deckDao.getAllMyCreatedDecks(userId);
       }
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.GET)
    public Deck getDeck(@PathVariable int deckId) {
        return deckDao.getDeck(deckId);
    };

    @RequestMapping(path = "/decks", method = RequestMethod.POST)
    public Deck createDeck(@RequestBody Deck deck) {
        return deckDao.createDeck(deck);
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.PUT)
    public boolean updateDeck(@RequestBody Deck deck, @PathVariable int deckId) {
        return deckDao.updateDeck(deck, deckId);
    }

    @RequestMapping(path = "/decks/tags/{tagName}", method = RequestMethod.GET)
    public Deck getDeckWithTagName(@PathVariable String tagName) {
        return deckDao.getDeckWithTagName(tagName);
    };



//    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.DELETE)
//    public boolean deleteDeck(@PathVariable int deckId) {
//        return true;
//    }

}
