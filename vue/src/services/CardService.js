import axios from 'axios';
// import { create } from 'core-js/core/object';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {

    getCards(deckId) {
        return http.get(`/decks/${deckId}/cards`);
    },

    getCardById(id) {
        return http.get(`/cards/${id}`);
    },

    addCard(card) {
        return http.post(`/cards`, card);
    },

    updateCard(card) {
        return http.put(`/cards/${card.cardId}`,card);
    },

    deleteCard(cardID) {
        return http.delete(`/cards/${cardID}`);
    }






}