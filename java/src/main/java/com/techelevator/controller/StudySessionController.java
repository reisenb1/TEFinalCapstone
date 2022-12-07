package com.techelevator.controller;


import com.techelevator.dao.StudySessionDao;
import com.techelevator.model.StudySession;
import com.techelevator.services.StudySessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudySessionController {


    private final StudySessionDao studySessionDao;
    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionDao studySessionDao, StudySessionService studySessionService) {
        this.studySessionDao = studySessionDao;
        this.studySessionService = studySessionService;
    }


    @RequestMapping(path = "/{userId}/studySessions", method = RequestMethod.GET)
    public List<StudySession> getAllStudySessionsFromUser(@PathVariable int userId) {
        return studySessionDao.getAllMyStudySessions(userId);

    }

    @RequestMapping(path = "/studySessions/{studySessionId}", method = RequestMethod.GET)
    public Deck getDeck(@PathVariable int deckId) {
        return deckDao.getDeck(deckId);
    };

    @RequestMapping(path = "/decks", method = RequestMethod.POST)
    public Deck createDeck(@RequestBody Deck deck) {
        return deckDao.createDeck(deck);
    }

    @RequestMapping(path = "/decks/{deckId}", method = RequestMethod.PUT)
    public boolean updateDeck(@RequestBody Deck deck, @PathVariable int deckId) {
        return deckDao.updateDeck(deck);
    }

    @RequestMapping(path = "/decks/tags/{tagName}", method = RequestMethod.GET)
    public Deck getDeckWithTagName(@PathVariable String tagName) {
        return deckDao.getDeckWithTagName(tagName);
    };

}
