<template>
  <div id="searchPage">
    <div id="search">
      <h2 id="searchTitle">Search:</h2>
      <input
        id="search-box"
        type="text"
        v-on:keyup.enter="retrieveSearchedDecks"
        placeholder="search by name or description..."
        v-model="searchTerm"
      />
    </div>

    <div id="searchResults">
      <div id="searchLinks" v-bind:key="deck.deckId" v-for="deck in this.decks">
        <div id="firstLine">
          <router-link
            v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }"
          >
            Deck ID: {{ deck.deckId }}
          </router-link>
          <router-link
            v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }"
          >
            Name: {{ deck.deckName }}
          </router-link>
        </div>
        <div id="secondLine">
          <router-link
            v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }"
          >
            Description: {{ deck.deckDescription }}
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";

export default {
  name: "Search",
  data() {
    return {
      decks: [],
      searchTerm: "",
    };
  },
  methods: {
    retrieveSearchedDecks() {
      DeckService.getAllSearchedDecks(this.searchTerm).then((response) => {
        this.decks = response.data;
      });
    },
    retrieveAllDecks() {
      // DeckService.getDecks(this.$store.getters.user.id).then((response) => {
      //     this.decks = response.data;
      DeckService.getAllDecks().then((response) => {
        this.decks = response.data;
      });
    },
  },
  created() {
    this.retrieveAllDecks();
  },
};
</script>

<style>
#firstLine {
  display: flex;
  justify-content: space-between;
}
#searchResults {
  /* display: flex; */
  /* justify-content: center; */
}
#searchLinks {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 60vw;
  background-color: #f9dcc4;
  margin: auto;
  margin-top: 10px;
}

#searchTitle {
  padding-right: 10px;
  color: #ffb5a7;
  text-shadow: 0 0 1.5px darkblue;
}

#searchPage {
  display: flex;
  /* justify-content: center; */
}

#search {
  display: flex;
  height: 60px;
  justify-content: center;
  align-items: center;
}

#search-box {
  width: 400px;
  border-radius: 15px;
  height: 35px;
  padding-left: 10px;
}
</style>