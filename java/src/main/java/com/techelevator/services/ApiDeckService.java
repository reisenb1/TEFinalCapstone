package com.techelevator.services;

import com.techelevator.model.ApiDeck;
import com.techelevator.model.ApiFlashcard;
import com.techelevator.model.Card;
import com.techelevator.model.Deck;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Component
public class ApiDeckService {

    private static final String API_BASE_URL = "https://opentdb.com/api.php?difficulty=easy&type=multiple&encode=base64";
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Update the given quiz with a set of questions from the external API.
     * See: https://opentdb.com/api_config.php
     */
    public Deck addQuestions(Deck deck, int numberOfQuestions) {
        // https://opentdb.com/api.php?difficulty=easy&type=multiple&encode=base64&amount=?&category=?

        int categoryId = ApiDeck.CATEGORY_CODES.get(deck.getCategory());
        String url = String.format("%s&amount=%d&category=%d", API_BASE_URL, numberOfQuestions, categoryId);

        ApiDeck apiDeck = restTemplate.getForObject(url, ApiDeck.class);

        deck.setQuestions(getQuizQuestions(apiDeck));
        return deck;
    }

    /**
     * @return a list of questions from the given external quiz
     */
    private static List<Card> getQuizQuestions(ApiDeck apiDeck) {
        List<Card> questions = new ArrayList<>();
        // for each question in the external quiz
        for (ApiFlashcard apiFlashcard : apiDeck.getQuestions()) {
            // add the new question and answers to the list of questions
            questions.add(getQuizQuestion(apiFlashcard));
        }
        return questions;
    }

    private static Card getQuizQuestion(ApiFlashcard apiFlashcard) {


        String answer = decode(apiFlashcard.getCorrectAnswer());
        String question = decode(apiFlashcard.getQuestion());

        return new Card(question, answer);
    }

    // decode the base64 encoded strings returned from external quiz API
    private static String decode(String s) {
        return new String(DECODER.decode(s));
    }
}
