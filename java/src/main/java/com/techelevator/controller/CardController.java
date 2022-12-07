package com.techelevator.controller;


import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CardController {

    private CardDao cardDao;

    public CardController(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @RequestMapping(path = "decks/{deckId}/cards", method = RequestMethod.GET)
    public List<Card> getAllCard(@PathVariable int deckId) {
        return cardDao.getAllCards(deckId);
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.GET)
    public Card getOneCard(@PathVariable int cardId) {
        return cardDao.getCard(cardId);
    }

    @RequestMapping(path = "/cards", method = RequestMethod.POST)
    public Card createCard(@RequestBody Card card) {
        return cardDao.createCard(card);
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.PUT)
    public boolean updateCard(@RequestBody Card card, @PathVariable int cardId) {
        return cardDao.updateCard(card,cardId);
    }

    @RequestMapping(path = "/cards/{cardId}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable int cardId) {
        return cardDao.deleteCard(cardId);
    }

}
