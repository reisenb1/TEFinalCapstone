import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:9000"
});


export default {
    getDecks(userId, saved) {
        return http.get(`/${userId}/decks`, { params: { saved } });
    },

    getDeckById(deckId) {
        return http.get(`/decks/${deckId}`)
    },

    addDeck(deck) {
        return http.post(`/decks`, deck)
    },

    updateDeck(deck) {
        return http.put(`/decks/${deck.deckId}`, deck);
    },

    getAllSearchedDecks(searchTerm) {
        return http.get(`/decks/search`, { params: { search: searchTerm } });
    },

    getAllDecks() {
        return http.get(`/decks`);
    },

    getMySearchedDecks(creatorId, mySearchTerm) {
        return http.get(`${creatorId}/decks/search`, { params: { search: mySearchTerm} });
    }

    // deleteDeck(deckId) {
    //     return http.delete(`/decks/${deckId}`)
    // }

}