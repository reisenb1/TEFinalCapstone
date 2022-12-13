package com.techelevator.controller;


import com.techelevator.dao.CardDao;
import com.techelevator.dao.DeckDao;
import com.techelevator.dao.JdbcDeckDao;
import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import com.techelevator.services.ApiDeckService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DeckController {

    private DeckDao deckDao;
    private ApiDeckService apiDeckService;
    private CardDao cardDao;

    public DeckController(DeckDao deckDao, ApiDeckService apiDeckService, CardDao cardDao) {
        this.deckDao = deckDao;
        this.apiDeckService = apiDeckService;
        this.cardDao = cardDao;
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

   @RequestMapping(path = "{creatorId}/decks/search", method = RequestMethod.GET)
    public List<Deck> getMySearchedDecks(@PathVariable int creatorId, @RequestParam(value = "search", required = false) String search) {
       if (search != null) {
           return deckDao.getMySearchDecks(creatorId, search);
       } else {
           return deckDao.getAllMyCreatedDecks(creatorId);
       }
    }


    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.GET)
    public Deck getDeck(@PathVariable int deckId) {
        return deckDao.getDeck(deckId);
    };


    @RequestMapping(path = "/decks", method = RequestMethod.POST)
    public Deck createDeck(@RequestBody Deck deck, @RequestParam(defaultValue = "10") int numberCards) {
        if(deck.getDeckName().length() == 0){
            deck.setDeckName(deck.getCategory().toString());
            Deck createdDeck = deckDao.createDeck(deck);
            createdDeck = apiDeckService.addQuestions(createdDeck,numberCards);

            for(Card card : createdDeck.getQuestions()) {
                card.setUserId(createdDeck.getCreatorId());
                card.setDeckId(createdDeck.getDeckId());
                cardDao.createCard(card);
            }

            return createdDeck;

        } else {
            return deckDao.createDeck(deck);
        }
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
