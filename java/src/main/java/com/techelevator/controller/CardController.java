package com.techelevator.controller;


import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {

    public CardController() {

    }

    @RequestMapping(path = "/cards", method = RequestMethod.GET)
    public void getAllCard() {

    };

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.GET)
    public Card getOneCard(@PathVariable int cardId) {
        return new Card();
    };

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.POST)
    public Card createCard(@RequestBody Card card, @PathVariable int cardId) {
        return new Card();
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.PUT)
    public Card updateCard(@RequestBody Card card, @PathVariable int cardId) {
        return new Card();
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable int cardId) {
        return true;
    }

}
