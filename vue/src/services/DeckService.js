import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});


export default {
    getDecks() {
        return http.get('/decks');
    },

    getDeckById(deckId) {
        return http.get(`/decks/${deckId}`)
    },

    addDeck(deck) {
        return http.post(`/decks/${deck}`)
    },

    updateDeck(deck) {
        return http.put(`/decks/${deck.id}`);
    },

    deleteDeck(deckId) {
        return http.delete(`/decks/${deckId}`)
    }

}