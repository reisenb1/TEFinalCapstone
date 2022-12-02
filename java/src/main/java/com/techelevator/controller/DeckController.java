package com.techelevator.controller;


import com.techelevator.model.Deck;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeckController {

    public DeckController() {
    }

    @RequestMapping(path = "/{userId}/decks", method = RequestMethod.GET)
    public void getAllDecksFromUser(@PathVariable int userId) {

    };

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.GET)
    public Deck getDeck(@PathVariable int deckId) {
        return new Deck();
    };

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.POST)
    public Deck createDeck(@RequestBody Deck deck, @PathVariable int deckId) {
        return new Deck();
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.PUT)
    public Deck updateDeck(@RequestBody Deck deck, @PathVariable int deckId) {
        return new Deck();
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.DELETE)
    public boolean deleteDeck(@PathVariable int deckId) {
        return true;
    }

}
